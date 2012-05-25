<html>
<head>
    <meta name="layout" content="main" />
    <title>BlueVia - Evernote Notebooks</title>
</head>
<body>
<div class="container">
    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>
    <div class="hero-unit">
        <h2>Evernote Notebooks</h2>
        <br/>
        <table class="table">
            <thead>
                <tr>
                    <th>Guid</th>
                    <th>Notebook Name</th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${notebooks}" status="i" var="notebook">
                    <tr>
                        <td class="span1"><g:link action="listNotes" id="${notebook.guid}">${notebook.guid}</g:link></td>
                        <td class="span2"><g:link action="listNotes" id="${notebook.guid}">${notebook.name}</g:link></td>
                    </tr>
                </g:each>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

