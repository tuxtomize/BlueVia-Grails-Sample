package com.bluevia.grails.sample

import com.evernote.edam.notestore.NoteFilter
import com.evernote.edam.notestore.NoteStore
import com.evernote.edam.type.NoteSortOrder
import com.evernote.edam.type.Notebook
import com.evernote.oauth.consumer.SimpleOAuthRequest
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.THttpClient
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.evernote.edam.type.Note

class EvernoteService {
    static transactional = false

    private static final String REDTAPPE_NOTEBOOK_NAME = 'Redtappe'
    private static final String PROCESSED_NOTES_TAG = 'redtappe_done'
    private static final String PENDING_NOTES_FILTER = "notebook:$REDTAPPE_NOTEBOOK_NAME -tag:$PROCESSED_NOTES_TAG"

    // TODO: Hacerlo publico. Así se puede incializar desde el controller y se pasa como parametro al resto de metodos.
    //       Evitamos así hacer tantos inicializaciones en cada request.
    private getNoteStoreClient(String evernoteShardId) {
        String noteStoreURL = "${ConfigurationHolder.config.evernote.noteStoreURLBase}${evernoteShardId}"
        THttpClient noteStoreTrans = new THttpClient(noteStoreURL)
        TBinaryProtocol noteStoreProt = new TBinaryProtocol(noteStoreTrans)
        new NoteStore.Client(noteStoreProt, noteStoreProt)
    }

    private SimpleOAuthRequest initOauthRequestor() {
        String requestTokenURL = ConfigurationHolder.config.evernote.requestTokenURL
        String consumerKey = ConfigurationHolder.config.evernote.consumer.key
        String consumerSecret = ConfigurationHolder.config.evernote.consumer.secret
        new SimpleOAuthRequest(requestTokenURL, consumerKey, consumerSecret, null)
    }

    def oAuthRequestToken() {
        // Send an OAuth message to the Provider asking for a new request token,
        // because we don't have access to the current user's account.
        def evernoteOauthRequestor = initOauthRequestor()
        String callbackURL = ConfigurationHolder.config.evernote.callbackURL
        evernoteOauthRequestor.setParameter("oauth_callback", callbackURL)
        def reply = evernoteOauthRequestor.sendRequest()
        reply.get("oauth_token")
    }

    def getAuthorizationURL(String requestToken) {
        "${ConfigurationHolder.config.evernote.authURL}${requestToken}"
    }

    def oAuthAccessToken(String requestToken, String verifier) {
        // Send an OAuth message to the Provider asking to exchange the
        // existing Request Token for an Access Token
        def evernoteOauthRequestor = initOauthRequestor()
        evernoteOauthRequestor.setParameter("oauth_token", requestToken)
        evernoteOauthRequestor.setParameter("oauth_verifier", verifier)

        def reply = evernoteOauthRequestor.sendRequest()

        ['accessToken': reply.get("oauth_token"),
         'shardId': reply.get("edam_shard"),
         'userId': reply.get("edam_userId")]
    }

    def listEvernoteBooks(String evernoteAccessToken, String evernoteShardId) {
        getNoteStoreClient(evernoteShardId).listNotebooks(evernoteAccessToken)
    }

    def listEvernoteNotesForBook(String evernoteAccessToken, String evernoteShardId, String notebookGuid) {
        def filter = new NoteFilter()
        filter.setNotebookGuid(notebookGuid)
        filter.setOrder(NoteSortOrder.CREATED.getValue())
        filter.setAscending(true)
        def noteList = getNoteStoreClient(evernoteShardId).findNotes(evernoteAccessToken, filter, 0, 100)
        noteList.getNotes()
    }

    def createRedtappeNotebookIfNotYetExist(String evernoteAccessToken, String evernoteShardId) {
        def redtappeNotebook = new Notebook()
        redtappeNotebook.setName(REDTAPPE_NOTEBOOK_NAME)
        def noteStoreClient = getNoteStoreClient(evernoteShardId)

        try {
            def createdNotebook = noteStoreClient.createNotebook(evernoteAccessToken, redtappeNotebook)
            createdNotebook.guid
        } catch (ex) {
            // the redtappe notebook already exists
            def notebooks = noteStoreClient.listNotebooks(evernoteAccessToken)
            notebooks.find { notebook ->
                notebook.name == REDTAPPE_NOTEBOOK_NAME
            }.guid
        }
    }

    def addNewNoteToNotebook(String evernoteAccessToken, String evernoteShardId, String title, String content, List<String> tags, String notebookGuid) {
        def newNote = new Note()
        newNote.setTitle(title)
        newNote.setContent("""<?xml version="1.0" encoding="UTF-8"?><!DOCTYPE en-note SYSTEM "http://xml.evernote.com/pub/enml2.dtd"><en-note></en-note>""")
        newNote.setCreated(new Date().time)
        newNote.setActive(true)
        newNote.setNotebookGuid(notebookGuid)
        newNote.setTagNames(tags)

        getNoteStoreClient(evernoteShardId).createNote(evernoteAccessToken, newNote)
    }

    def getEvernoteNoteDetail(String evernoteAccessToken, String evernoteShardId, String noteGuid) {
        getNoteStoreClient(evernoteShardId).getNote(evernoteAccessToken, noteGuid, true, true, false, false)
    }

    def getEvernoteNoteTags(String evernoteAccessToken, String evernoteShardId, String noteGuid) {
        getNoteStoreClient(evernoteShardId).getNoteTagNames(evernoteAccessToken, noteGuid)
    }

    def getResource(String evernoteAccessToken, String evernoteShardId, String resourceGuid) {
        getNoteStoreClient(evernoteShardId).getResource(evernoteAccessToken, resourceGuid, true, false, true, false)
    }

    def getResourceThumbnailUrl(String evernoteShardId, String resourceGuid) {
        "${ConfigurationHolder.config.evernote.urlBase}/shard/${evernoteShardId}/thm/res/${resourceGuid}"
    }

    byte[] getEvernoteSnapshot(String evernoteShardId, String evernoteAccessToken, String resourceGuid) {
        def evernoteBaseURL = ConfigurationHolder.config.evernote.urlBase
        def snapshotURL = "${evernoteBaseURL}/shard/${evernoteShardId}/thm/res/${resourceGuid}?auth=${evernoteAccessToken.encodeAsURL()}"

        URLConnection connection = new URL(snapshotURL).openConnection()
        connection.setDoOutput(true);  // implies POST method
        connection.setUseCaches(false)
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
        connection.connect()

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream.bytes
        } else {
            // the remote service returned some HTTP error code.
            log.error("Evernote BAD REQUEST!!!: ${connection.responseCode}")
            null
        }
    }
}
