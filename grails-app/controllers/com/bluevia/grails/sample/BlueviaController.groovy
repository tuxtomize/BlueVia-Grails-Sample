package com.bluevia.grails.sample

class BlueviaController {
    BlueviaInboxService blueviaInboxService

    def inboundSMSs = {
        try {
            def inboundSmss = blueviaInboxService.requestInboundSMSs()
            blueviaInboxService.processInboundSMSs(inboundSmss)
            render view:'listSMSs', model:[smsList: inboundSmss]
        } catch (ex) {
            log.error "BlueVia service connection error", ex
            flash.message = 'Something went wrong during the BlueVia service call. Please, try again.'
            redirect uri: '/'
        }
    }

    def inboundMMSs = {
        try {
            def inboundMmss = blueviaInboxService.requestInboundMMSs()
            //blueviaInboxService.processInboundMMSs(inboundMmss)
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
