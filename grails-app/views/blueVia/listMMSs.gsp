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
        <h2>MMS's</h2>
        <br/>
        <g:each in="${mmsList}" status="i" var="mms">
            <div class="well" style="height: 200px;">
                <table class="condensed-table" style="border-style: hidden;">
                    <%--g:if test="${resourceMime?.startsWith('image/') || resourceMime?.equals('application/pdf')}"--%>
                        <tr style="border-bottom-style: hidden;">
                            <td rowspan="8">
                                <img src="/images/no_image.jpg" width="140"/>
                            </td>
                            <td style="border-bottom-style: hidden;"></td>
                            <td style="border-bottom-style: hidden;"></td>
                        </tr>
                    <%--/g:if--%>
                    <tr>
                        <td>Message Identifier</td>
                        <td>${mms.messageIdentifier}</td>
                    </tr>
                    <tr>
                        <td>Subject</td>
                        <td>${mms.subject}</td>
                    </tr>
                    <tr>
                        <td>Message</td>
                        <td>${mms.message}</td>
                    </tr>
                    <tr>
                        <td>Origin Alias</td>
                        <td>${mms.fromAlias}</td>
                    </tr>
                    <tr>
                        <td>Origin Phone number</td>
                        <td>${mms.fromPhoneNumber}</td>
                    </tr>
                    <tr>
                        <td>Destination Phone number</td>
                        <td>${mms.toPhoneNumber}</td>
                    </tr>
                    <tr>
                        <td>Date</td>
                        <td>${mms.date}</td>
                    </tr>
                </table>
            </div>
        </g:each>
    </div>
</body>
</html>
