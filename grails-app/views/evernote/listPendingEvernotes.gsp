<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>

<html>
<head>
    <meta name="layout" content="main" />
    <title>Redtappe - Pending Evernotes</title>
</head>
<body>
    <g:if test="${notes.size() == 0}">
        <div class="alert-message info fade in" data-alert="alert">
            <a class="close" href="#">×</a>
            <p>You are synchronized with your Evernote inbox. No pending notes to import.</p>
        </div>
    </g:if>

    <g:if test="${flash.message}">
        <div class="alert-message info fade in" data-alert="alert">
            <a class="close" href="#">×</a>
            <p>${flash.message}</p>
        </div>
    </g:if>

    <div>
        <g:if test="${notes}">
            <div id="results">
                <div id="report" class="clearfix">
                    <div class="title">
                        <div style="font-size:16px; margin: 13px 20px;">Evernote incoming papers</div>
                    </div>
                </div>
            <g:each in="${notes}" status="i" var="note">
                <g:set var="resource" value="${note.getResources()?.getAt(0)}"/>
                <g:set var="resourceGuid" value="${resource?.guid}"/>
                <g:set var="resourceMime" value="${resource?.getMime()}"/>

                <%-- TODO: Fix repeat IF --%>
                <article class="paper clearfix">
                    <g:if test="${resourceMime?.startsWith('image/') || resourceMime?.equals('application/pdf')}">
                        <a class="thumbnail" href="${ConfigurationHolder.config.pwbox.evernote.urlBase}/shard/${session.user.evernoteShardId}/thm/res/${resourceGuid}">
                            <span class="img-wrapper">
                                <img src="${ConfigurationHolder.config.pwbox.evernote.urlBase}/shard/${session.user.evernoteShardId}/thm/res/${resourceGuid}" width="170" border="0" alt="filename: ${note.getResources()?.getAt(0)?.getAttributes()?.getFileName()}" />
                                <span class="icon-zoom-in"></span>
                            </span>
                    </g:if>
                    </a>
                    <div class="content">
                        <h3>${note.getTitle()}</h3>
                        <dl>
                            <g:if test="${note.getTagNames()}">
                                <dt>Evernote tags: </dt>
                                <dd><g:each in="${note.getTagNames()}" var="tag">${tag}, </g:each></dd>
                            </g:if>
                            <g:if test="${note.getCreated()}">
                                <dt>Date: </dt>
                                <dd><g:formatDate date="${note.getCreated()}" format="dd/MM/yyyy"/></dd>
                            </g:if>
                            <g:if test="${resourceMime?.startsWith('image/') || resourceMime?.equals('application/pdf')}">
                                <dt>Attached File: </dt>
                                <dd>
                                <g:link action="downloadResourceContent" id="${resourceGuid}">
                                    ${(resource?.getAttributes()?.getFileName() ?: resourceGuid)}
                                </g:link>
                                </dd>
                            </g:if>
                        </dl>
                    </div>

                    <g:form class="paper-actions">
                        <g:hiddenField name="id" value="${note.guid}" />
                        <button name="_action_importNote" class="icon-ok edit" title="Import ${note.getTitle()}"></button>
                        <button name="_action_discardNote" class="icon-remove delete" title="Discard ${note.getTitle()}" onclick="return confirm('Are you sure?');"></button>
                    </g:form>
                </article>
            </g:each>
            </div>
        </g:if>
    </div>
</body>
</html>
