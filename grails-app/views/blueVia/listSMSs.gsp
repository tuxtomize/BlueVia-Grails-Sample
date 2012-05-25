<html>
<head>
    <meta name="layout" content="main" />
    <title>BlueVia - SMS Inbox</title>
</head>
<body>
    <div class="container">
        <g:if test="${flash.message}">
            <div class="alert alert-info">
                ${flash.message}
            </div>
        </g:if>
        <div class="hero-unit">
            <h2>SMS's</h2>
            <br/>
            <g:each in="${smsList}" status="i" var="sms">
                <div class="well">
                    <div class="row">
                        <div class="span2">Message</div>
                        <div class="span8">${sms.message}</div>
                    </div>
                    <div class="row">
                        <div class="span2">Origin Alias</div>
                        <div class="span8">${sms.fromAlias}</div>
                    </div>
                    <div class="row">
                        <div class="span2">Origin Phone number</div>
                        <div class="span8">${sms.fromPhoneNumber}</div>
                    </div>
                    <div class="row">
                        <div class="span2">Destination Phone number</div>
                        <div class="span8">${sms.toPhoneNumber}</div>
                    </div>
                    <div class="row">
                        <div class="span2">Date</div>
                        <div class="span8">${sms.date}</div>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</body>
</html>
