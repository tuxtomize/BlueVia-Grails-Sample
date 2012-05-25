package com.bluevia.grails.sample

class BlueviaInboxService {
    static transactional = false

    def blueviaService
    def evernoteService

    def requestInboundSMSs() {
        blueviaService.getPendingSMSs()
    }

    def processInboundSMSs(pendingSmss) {
        pendingSmss.each { sms ->
            log.debug "Evernoting SMS from ${sms.fromPhoneNumber}/${sms.fromAlias} in the authorized Evernote account"
            storeMessageInEvernoteUserAccount(sms)
        }
    }

    def requestInboundMMSs() {
        blueviaService.getPendingMMSs()
    }

    def processInboundMMSs(pendingMmss) {
        pendingMmss.each { mms ->
            log.debug "Evernoting SMS from ${mms.fromPhoneNumber}/${mms.fromAlias} in the authorized Evernote account"
            storeMessageInEvernoteUserAccount(mms)
        }
    }

    private storeMessageInEvernoteUserAccount(message) {
        try {
            def tags = extractTagsFromMessage(message.message)
            evernoteService.addNewNoteToNotebook(session.evernoteAccessToken, session.evernoteShardId, 
                                                 message.message, null, tags, session.redtappeNotebookGuid)
        } catch (ex) {
            log.error "Error evernoting message. Probably the user hasn't yet activated their Evernote account", ex
        }
    }

    private List<String> extractTagsFromMessage(message) {
        def tags = []
        def tagsMatcher = (message =~ /#(\w+)/)
        tagsMatcher.each { tag ->
            tags << tag[1]
        }
        tags
    }
}
