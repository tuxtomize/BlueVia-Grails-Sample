package com.bluevia.grails.sample

class BlueviaController {
    BlueviaInboxService blueviaInboxService

    def inboundSMSs = {
        try {
            def inboundSmss = blueviaInboxService.requestInboundSMSs()

            if (session.evernoteAccessToken) {
                blueviaInboxService.processInboundSMSs(inboundSmss, session)
            } else {
                flash.message = 'This Inbound messages are not yet stored in Evernote. You need to authenticate your Evernote account first.'
            }

            render view:'listSMSs', model:[smsList: inboundSmss]
        } catch (ex) {
            log.error "BlueVia service connection error", ex
            flash.message = 'Something went wrong during the BlueVia service call. Please, try again.'
            redirect uri: '/'
        }
    }

    def inboundMMSs = {
        try {
            // TODO
            def inboundMmss = blueviaInboxService.requestInboundMMSs()
            // blueviaInboxService.processInboundMMSs(inboundMmss, session)
            render view:'listMMSs', model:[smsList: inboundMmss]
        } catch (ex) {
            log.error "BlueVia service connection error", ex
            flash.message = 'Something went wrong during the BlueVia service call. Please, try again.'
            redirect uri: '/'
        }
    }

    def index = {
        redirect action: 'inboundSMSs'
    }
}
