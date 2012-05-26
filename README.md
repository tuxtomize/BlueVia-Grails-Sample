##BlueVia Grails application sample

This is a Grails webapp demo coded by the [Redtappe](http://beta.redtappe.com/) team.

It aims to show, in a simple way, *how to read and process inbound messages from the BlueVia platform*.

First, It *uses the BlueVia API* to retrieve the pending inbound messages for a given application keyword, in a given country.
Then, it *uses the Evernote API* to store the BlueVia inbound messages in the default Evernote notebook (for a previously authorized Evernote account).


## Configure your API keys for BlueVia and Evernote
Obtain your own API keys for both services [BlueVia](https://bluevia.com/en/knowledge/APIs.Get-your-API-key) and [Evernote](http://dev.evernote.com/).

### Set your personal API keys in the *Config.groovy* file

For BlueVia:

    bluevia {
        // use your own credentials here
        consumer.key = '__ your consumer key here __'
        consumer.secret = '__ your consumer secret here __'
    }


For Evernote:

    evernote {
        // use your own credentials here
        consumer.key = '__ your consumer key here __'
        consumer.secret = '__ your consumer secret here __'
    }



## Install the Grails framework

[Download](http://www.grails.org/Download) and [configure](http://www.grails.org/doc/latest/guide/gettingStarted.html#requirements) the Grails framework.



## Run the Grails app

    $> grails run-app
    
