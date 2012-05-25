<html>
<head>
    <meta name="layout" content="main" />
    <title>BlueVia - Evernote Note</title>
</head>
<body>
<div class="container">
    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>
    <div class="hero-unit">
        <h2>Note detail for <em>${note.title}</em></h2>
        <br/>
        <div class="well">
            <div class="row">
                <div class="span2">Guid</div>
                <div class="span8">${note.guid}</div>
            </div>
            <div class="row">
                <div class="span2">Title</div>
                <div class="span8">${note.title}</div>
            </div>
            <div class="row">
                <div class="span2">Created</div>
                <div class="span8">${note.created}</div>
            </div>
            <div class="row">
                <div class="span2">Resource Name</div>
                <div class="span8">
                    <g:link action="downloadResourceContent" id="${note.getResources()?.getAt(0)?.guid}">
                        ${note.getResources()?.getAt(0)?.getAttributes()?.getFileName()}
                    </g:link>
                </div>
            </div>
            <div class="row">
                <div class="span2">Resource Mime</div>
                <div class="span8">${note.getResources()?.getAt(0)?.getMime()}</div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

