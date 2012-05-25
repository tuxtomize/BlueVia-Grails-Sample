<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evernote.label', default: 'Evernote')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="home" controller="paper" action="list"><g:message code="default.home.label"/></g:link></span>
            <span class="menuButton"><g:link class='create' action='oauthLogin'>Authorize Evernote Account</g:link></span>
            <span class="menuButton"><g:link class="list" action="listNotebooks">List Evernote Notebooks</g:link></span>
        </div>
        <div class="body">
            <h1>Evernote Oauth Integration Test</h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
        </div>
    </body>
</html>
