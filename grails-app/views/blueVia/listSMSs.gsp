<html>
<head>
    <meta name="layout" content="main" />
    <title>Redtappe - Pending Inbound MMS's</title>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="alert-message info fade in" data-alert="alert">
            <a class="close" href="#">×</a>
            <p>${flash.message}</p>
        </div>
    </g:if>
    <div class="hero-unit">
        <h2>SMS's</h2>
        <br/>
        <g:each in="${smsList}" status="i" var="sms">
            <div class="well" style="height: 120px;">
                <table class="condensed-table" style="border-style: hidden;">
                    <tr>
                        <td>Message</td>
                        <td>${sms.message}</td>
                    </tr>
                    <tr>
                        <td>Origin Alias</td>
                        <td>${sms.fromAlias}</td>
                    </tr>
                    <tr>
                        <td>Origin Phone number</td>
                        <td>${sms.fromPhoneNumber}</td>
                    </tr>
                    <tr>
                        <td>Destination Phone number</td>
                        <td>${sms.toPhoneNumber}</td>
                    </tr>
                    <tr>
                        <td>Date</td>
                        <td>${sms.date}</td>
                    </tr>
                </table>
            </div>
        </g:each>
    </div>
</body>
</html>
