// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']


// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// log4j configuration
log4j = {
    appenders {
        console name: 'stdout', layout: pattern(conversionPattern: '[%d{dd/MM/yyyy HH:mm:ss} | %-5p | %c{1}] %m%n')
    }

    debug  'grails.app.services'
    debug  'grails.app.controllers'

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
           'org.codehaus.groovy.grails.commons', // core / classloading
           'org.codehaus.groovy.grails.plugins', // plugins
           'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

grails.app.context = '/'
grails.serverURL = "http://localhost:8080"

bluevia {
    // use your own credentials here
    // you can get tons of them in https://bluevia.com/en/knowledge/APIs.Get-your-API-key :)
    consumer.key = '__________ your consumer key here ___________'
    consumer.secret = '__________ your consumer secret here ___________'

    countryCode.vivo.Brasil = '55281'
    countryCode.movistar.Spain = '34217040'
    countryCode.movistar.Argentina = '546780'
    countryCode.movistar.Chile = '5698765'
    countryCode.movistar.Colombia = '572505'
    countryCode.movistar.Mexico = '524040'
    countryCode.o2.UK = '445480605'
    countryCode.o2.Germany = '493000'

    smsService.outURL = "https://api.bluevia.com/services/REST/SMS/inbound/${countryCode.movistar.Spain}/messages?version=v1&alt=json"
    mmsService.outURL = "https://api.bluevia.com/services/REST/MMS/inbound/${countryCode.movistar.Spain}/messages?version=v1&alt=json"

    // requestTokenURL = 'https://api.bluevia.com/services/REST/Oauth/getRequestToken/'
    // accessTokenURL = 'https://api.bluevia.com/services/REST/Oauth/getAccessToken/'
    // authoriseURL = 'https://connect.bluevia.com/authorise/'
    // moKeyword = 'your_app_keyword'
    // sandboxMOKeyword = 'SANDyour_app_keyword'
}

evernote {
    // use your own credentials here
    // you can get tons of them in http://dev.evernote.com/ :)
    consumer.key = '__________ your consumer key here ___________'
    consumer.secret = '__________ your consumer secret ___________'

    // change to 'https://www.evernote.com' when you are ready to fly
    urlBase = "https://sandbox.evernote.com"

    noteStoreURLBase = "${urlBase}/edam/note/"
    requestTokenURL = "${urlBase}/oauth"
    callbackURL = "${grails.serverURL}/evernote/oauthCallback"
    authURL = "${urlBase}/OAuth.action?oauth_token="
}
