package com.bluevia.grails.sample

import org.codehaus.groovy.grails.commons.ConfigurationHolder
import oauth.signpost.OAuthConsumer
import oauth.signpost.basic.DefaultOAuthConsumer
import oauth.signpost.signature.HmacSha1MessageSigner
import groovy.json.JsonSlurper

class BlueviaService {
    static transactional = false

    List<Sms> getPendingSMSs() {
        def smsList = []

        String blueviaBaseURL = ConfigurationHolder.config.bluevia.smsService.outURL
        def oauthConsumerKey = ConfigurationHolder.config.bluevia.consumer.key
        def oauthConsumerSecret = ConfigurationHolder.config.bluevia.consumer.secret

        OAuthConsumer apiConsumer = new DefaultOAuthConsumer(oauthConsumerKey, oauthConsumerSecret)
        apiConsumer.setMessageSigner(new HmacSha1MessageSigner())
        apiConsumer.setTokenWithSecret(null,"")
        
        HttpURLConnection connection = (HttpURLConnection)new URL(blueviaBaseURL).openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        apiConsumer.sign(connection)
        connection.connect()

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            def serviceResponse = connection.inputStream.text

            // Some sample json reponses. For testing.
            // def serviceResponse = """{"receivedSMS":{"receivedSMS":[{"message":"Redtappe msg 1","originAddress":{"alias":"435954FA2B5DA30251CF731741BC99A0"},"destinationAddress":{"phoneNumber":"34217040"},"dateTime":"2012-04-24T18:22:29.086Z"},{"message":"Redtappe msg 2","originAddress":{"alias":"435954FA2B5DA30251CF731741BC99A0"},"destinationAddress":{"phoneNumber":"34217040"},"dateTime":"2012-04-24T18:22:43.158Z"}]}}"""
            // def serviceResponse = """{"receivedSMS":{"receivedSMS":{"message":"Redtappe test with amount 99.90 tags and redtappe #Tag1 #Tag2 #Tag3#Tag4","originAddress":{"alias":"435954FA2B5DA30251CF731741BC99A0"},"destinationAddress":{"phoneNumber":"34217040"},"dateTime":"2012-04-24T18:07:36.097Z"}}}"""

            log.info "BlueVia service response: $serviceResponse"

            def jsonResponse = new JsonSlurper().parseText(serviceResponse)
            def receivedData = jsonResponse.receivedSMS.receivedSMS

            if (receivedData instanceof java.util.List) {
                receivedData.each { message ->
                    smsList << getSmsfromJson(message)
                }
            } else if (receivedData instanceof java.util.HashMap) {
                smsList << getSmsfromJson(receivedData)
            }

            log.info "${smsList.size()} pending SMS's found"

        } else if (connection.responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            log.info "No pending SMS's found"
        } else {
            // the remote service returned some HTTP error code.
            log.error "BlueVia Bad Request: ${connection.responseCode}. ${connection.toString()}. ${connection.getHeaderFields()}"
        }
        
        smsList
    }

    List<Mms> getPendingMMSs() {
        def mmsList = []

        def blueviaBaseURL = ConfigurationHolder.config.pwbox.bluevia.mmsService.outURL
        def oauthConsumerKey = ConfigurationHolder.config.pwbox.bluevia.consumer.key
        def oauthConsumerSecret = ConfigurationHolder.config.pwbox.bluevia.consumer.secret

        OAuthConsumer apiConsumer = new DefaultOAuthConsumer(oauthConsumerKey, oauthConsumerSecret)
        apiConsumer.setMessageSigner(new HmacSha1MessageSigner())
        apiConsumer.setTokenWithSecret(null,"")

        HttpURLConnection connection = (HttpURLConnection)new URL(blueviaBaseURL).openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        apiConsumer.sign(connection)
        connection.connect()

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            /*
            Some sample responses.

            def serviceResponse = """{"receivedMessages":{
                                    "receivedMessages":[
                                            {
                                                "messageIdentifier":"06603100100226160900",
                                                "destinationAddress":{"phoneNumber":"524040"},
                                                "originAddress":{"phoneNumber":"524794786537"},
                                                "subject":"MYKEYWORD MMS subject",
                                                "dateTime":"2011-03-21T12:51:01.000-03:00"
                                            },
                                            {
                                                "messageIdentifier":"06603100100226160905",
                                                "destinationAddress":{"phoneNumber":"524040"},
                                                "originAddress":{"phoneNumber":"524759026184"},
                                                "subject":"MYKEYWORD MMS subject",
                                                "dateTime":"2011-03-21T12:46:10.000-03:00"
                                            }]
                                }}"""
            */

            def serviceResponse = connection.inputStream.text

            log.info "BlueVia service response: $serviceResponse"

            def jsonResponse = new JsonSlurper().parseText(serviceResponse)
            def receivedData = jsonResponse.receivedMessages.receivedMessages

            if (receivedData instanceof java.util.List) {
                receivedData.each { message ->
                    mmsList << getMmsfromJson(message)
                }
            } else if (receivedData instanceof java.util.HashMap) {
                mmsList << getMmsfromJson(receivedData)
            }

            log.info "${mmsList.size()} pending MMS's found"

        } else if (connection.responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            log.info "No pending MMS's found"
        } else {
            // the remote service returned some HTTP error code.
            log.error "BlueVia Bad Request: ${connection.responseCode}. ${connection.toString()}. ${connection.getHeaderFields()}"
        }

        mmsList
    }

    private getSmsfromJson(jsonMessage) {
        def sms = new Sms()
        sms.message = jsonMessage.message
        sms.fromAlias = jsonMessage.originAddress.alias
        sms.fromPhoneNumber = jsonMessage.originAddress.phoneNumber
        sms.toPhoneNumber = jsonMessage.destinationAddress.phoneNumber
        sms.date = jsonMessage.dateTime
        sms
    }

    private getMmsfromJson(jsonMessage) {
        def mms = new Mms()
        mms.messageIdentifier = jsonMessage.messageIdentifier
        mms.subject = jsonMessage.subject
        mms.message = jsonMessage.message
        mms.fromAlias = jsonMessage.originAddress.alias
        mms.fromPhoneNumber = jsonMessage.originAddress.phoneNumber
        mms.toPhoneNumber = jsonMessage.destinationAddress.phoneNumber
        mms.date = jsonMessage.dateTime
        mms
    }
}
