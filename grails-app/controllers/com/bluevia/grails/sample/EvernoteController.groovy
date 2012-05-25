package com.bluevia.grails.sample

class EvernoteController {
    def evernoteService

    def oauthLogin = {
        try {
            def requestToken = evernoteService.oAuthRequestToken()
            redirect url: evernoteService.getAuthorizationURL(requestToken)
        } catch (ex) {
            log.error "OauthLogin error", ex
            flash.message = 'Something went wrong during the Evernote Oauth process. Please, try again.'
            redirect uri:'/'
        }
    }

    def oauthCallback = {
        def requestToken = request.getParameter('oauth_token')
        def verifier = request.getParameter('oauth_verifier')

        if (verifier) {
            def oauthResult = evernoteService.oAuthAccessToken(requestToken, verifier)

            // Almacenamos los datos de auth evernote en la cuenta de usuario.
            session.evernoteAccessToken = oauthResult['accessToken']
            session.evernoteShardId = oauthResult['shardId']

            // Creamos el notebook para Redtappe (si no existe aun).
            def redtappeNotebookGuid = evernoteService.createRedtappeNotebookIfNotYetExist(session.evernoteAccessToken, session.evernoteShardId)
            session.redtappeNotebookGuid = redtappeNotebookGuid

            log.info "Done!. Evernote user account authenticated."
            flash.message = "The Evernote inbox is now working for Redtappe. You can store your papers in the 'Redtappe' Evernote notebook."
            redirect action:'listPendingEvernotes'
        } else {
            log.error "Evernote access denied."
            flash.message = 'Evernote access denied.'
            redirect uri:'/'
        }
    }

    def index = {
        redirect action:'listNotebooks'
    }

    def listNotebooks = {   
        try {
            [notebooks: evernoteService.listEvernoteBooks(session.evernoteAccessToken, session.evernoteShardId)]
        } catch (ex) {
            log.error ex
            redirect action:'oauthLogin'
        }
    }

    def listNotes = {
        try {
            [notes: evernoteService.listEvernoteNotesForBook(session.evernoteAccessToken, session.evernoteShardId, params.id)]
        } catch (ex) {
            log.error ex
            redirect action:'oauthLogin'
        }
    }

    def showNote = {
        try {
            [note: evernoteService.getEvernoteNoteDetail(session.evernoteAccessToken, session.evernoteShardId, params.id)]
        } catch (ex) {
            log.error ex
            redirect action:'oauthLogin'
        }
    }

    def downloadResourceContent = {
        try {
            def resource = evernoteService.getResource(session.evernoteAccessToken, session.evernoteShardId, params.id)
            if (resource) {
                response.setHeader("Content-Disposition", "attachment; filename=\"${(resource.attributes?.fileName ?: resource.guid)}\"")
                response.setContentType(resource.mime)
                response.outputStream << resource.data.body
                response.outputStream.flush()
            } else {
                flash.message = 'Resource not found!'
                redirect uri:'/'
            }
        } catch (ex) {
            log.error ex
            flash.message = 'Error while accessing the requested resource.'
            redirect uri:'/'
        }
    }

    def resourceThumbnailUrl = {
        render evernoteService.getResourceThumbnailUrl(session.evernoteShardId, params.id) as String
    }
}
