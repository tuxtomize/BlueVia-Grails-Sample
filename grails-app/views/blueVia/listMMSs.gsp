<html>
<head>
    <meta name="layout" content="main" />
    <title>BlueVia - MMS Inbox</title>
</head>
<body>
<div class="container">
    <g:if test="${flash.message}">
        <div class="alert alert-info">
            ${flash.message}
        </div>
    </g:if>
    <div class="hero-unit">
        <h2>MMS's</h2>
        <br/>
        <g:each in="${smsList}" status="i" var="mms">
            <div class="well">
                <div class="row">
                    <div class="span2">Message Identifier</div>
                    <div class="span8">${mms.messageIdentifier}</div>
                </div>
                <div class="row">
                    <div class="span2">Subject</div>
                    <div class="span8">${mms.subject}</div>
                </div>

                <div class="row">
                    <div class="span2">Message</div>
                    <div class="span8">${mms.message}</div>
                </div>
                <div class="row">
                    <div class="span2">Origin Alias</div>
                    <div class="span8">${mms.fromAlias}</div>
                </div>
                <div class="row">
                    <div class="span2">Origin Phone number</div>
                    <div class="span8">${mms.fromPhoneNumber}</div>
                </div>
                <div class="row">
                    <div class="span2">Destination Phone number</div>
                    <div class="span8">${mms.toPhoneNumber}</div>
                </div>
                <div class="row">
                    <div class="span2">Date</div>
                    <div class="span8">${mms.date}</div>
                </div>
            </div>
        </g:each>
    </div>
</div>
</body>
</html>
