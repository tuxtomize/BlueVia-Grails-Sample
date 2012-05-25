<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>BlueVia Grails Sample</title>
    </head>
    
    <body>
        <div class="container">
            <g:if test="${flash.message}">
                <div class="alert alert-info">
                    ${flash.message}
                </div>
            </g:if>
            <div class="hero-unit">
                <p>
                    This is a Grails demo app coded by the <a href="http://beta.redtappe.com/" target="_blank">Redtappe</a> team.
                </p>
                <p>
                    It aims to show, in a simple way, <strong>how to read and process inbound messages from the BlueVia platform</strong>.
                </p>
                <p>
                    First, It <strong>uses the BlueVia API</strong> to retrieve the pending inbound messages for a given application keyword, in a given country.
                    Then, it <strong>uses the Evernote API</strong> to store the BlueVia inbound messages in the default Evernote notebook (for a previously authorized Evernote account).
                </p>
                <p>
                    So, don't forget to obtain and configure your own API keys for both services (<a href="https://bluevia.com/en/knowledge/APIs.Get-your-API-key" target="_blank">BlueVia</a> and <a href="http://dev.evernote.com/" target="_blank">Evernote</a>).
                </p>
                <br/><br/>  
                <div class="row offset2">
                    <a class="btn btn-primary btn-large span2" href="/bluevia/inbox"><strong>BlueVia Inbox</strong></a>
                    <a class="btn btn-success btn-large span2" href="/evernote/inbox"><strong>Evernote Store</strong></a>
                </div>
            </div>
        </div>
        
        <script src="/js/bootstrap.js"></script>
        <script src="/js/bootstrap.min.js"></script>
    </body>
</html>