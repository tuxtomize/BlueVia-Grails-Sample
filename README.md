##BlueVia Grails app sample

This is a Grails webapp demo coded by the [Redtappe](http://beta.redtappe.com/) team.

It aims to show, in a simple way, **how to read and process inbound messages from the BlueVia platform**.

First, It **uses the BlueVia API** to retrieve the pending inbound messages for a given application keyword, in a given country.
Then, it **uses the Evernote API** to store the BlueVia inbound messages in a given Evernote notebook (for a previously authorized Evernote account).


## Configure your API keys

### Obtain your own API keys for both services:

- [BlueVia](https://bluevia.com/en/knowledge/APIs.Get-your-API-key)
- [Evernote](http://dev.evernote.com/)


### Set your API keys in the *Config.groovy* file:

For BlueVia:

    bluevia {
        // use your own credentials here
        consumer.key = '__ your consumer key here __'
        consumer.secret = '__ your consumer secret here __'
        ...
    }

For Evernote:

    evernote {
        // use your own credentials here
        consumer.key = '__ your consumer key here __'
        consumer.secret = '__ your consumer secret here __'
        ...
    }


## Configure your country for the BlueVia service

A set of BlueVia country codes is provided in the *Config.groovy* file:

    bluevia {
        ...
        countryCode.vivo.Brasil = '55281'
        countryCode.movistar.Spain = '34217040'
        countryCode.movistar.Argentina = '546780'
        countryCode.movistar.Chile = '5698765'
        countryCode.movistar.Colombia = '572505'
        countryCode.movistar.Mexico = '524040'
        countryCode.o2.UK = '445480605'
        countryCode.o2.Germany = '493000'

        smsService.outURL = "https://api.bluevia.com/services/REST/SMS/inbound/${countryCode.movistar.Spain}/messages?version=v1&alt=json"
        ...
    }


## Install Grails

[Download](http://www.grails.org/Download) and [configure](http://www.grails.org/doc/latest/guide/gettingStarted.html#requirements) the Grails framework.


## Run the Grails app

    $> grails run-app
    
