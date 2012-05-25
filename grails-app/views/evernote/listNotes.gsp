<html>
<head>
    <meta name="layout" content="main" />
    <title>BlueVia - Evernote Notes</title>
</head>
<body>
<div class="container">
    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>
    <div class="hero-unit">
        <h2>Notes for the Notebook <em>${notes[0]?.getNotebookGuid()}</em></h2>
        <br/>
        <table class="table">
            <thead>
            <tr>
                <th>Guid</th>
                <th>Note Title</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${notes}" status="i" var="note">
                <tr>
                    <td class="span1"><g:link action="showNote" id="${note.guid}">${note.guid}</g:link></td>
                    <td class="span2"><g:link action="showNote" id="${note.guid}">${note.title}</g:link></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>


