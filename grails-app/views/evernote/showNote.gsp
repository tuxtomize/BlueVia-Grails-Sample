<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'evernote.label', default: 'Evernote')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="home" controller="paper" action="list"><g:message code="default.home.label"/></g:link></span>
            <span class="menuButton"><g:link class='create' action='oauthLogin'>Authorize Evernote Account</g:link></span>
            <span class="menuButton"><g:link class="list" action="listNotebooks">List Evernote Notebooks</g:link></span>
        </div>
        <div class="body">
            <h1>Evernote Note detail for '${note.getTitle()}'</h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Guid</td>
                            <td valign="top" class="value">${note.getGuid()}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Title</td>
                            <td valign="top" class="value">${note.getTitle()}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Created</td>
                            <td valign="top" class="value">${note.getCreated()}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Resource Name</td>
                            <td valign="top" class="value">
                                <g:link action="downloadResourceContent" id="${note.getResources()?.getAt(0)?.guid}">
                                    ${note.getResources()?.getAt(0)?.getAttributes()?.getFileName()}
                                </g:link>
                            </td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Resource Mime</td>
                            <td valign="top" class="value">${note.getResources()?.getAt(0)?.getMime()}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <br/>
            </div>
        </div>
    </body>
</html>
