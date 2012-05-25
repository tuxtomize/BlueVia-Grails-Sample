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
            <h1>Evernote Notes for the Notebook '${notes[0]?.getNotebookGuid()}'</h1>
            <br/>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <br/>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="title" title="Note Title" />
                            <g:sortableColumn property="guid" title="Guid" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${notes}" status="i" var="note">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="showNote" id="${note.guid}">${note.title}</g:link></td>
                            <td><g:link action="showNote" id="${note.guid}">${note.guid}</g:link></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
