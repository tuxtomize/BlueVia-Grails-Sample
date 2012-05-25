<html>
<head>
    <meta name="layout" content="main" />
    <title>Redtappe - SMS Inbox state</title>
</head>
<body>
    <g:if test="${flash.message}">
        <div class="alert-message info fade in" data-alert="alert">
            <a class="close" href="#">×</a>
            <p>${flash.message}</p>
        </div>
    </g:if>
    <div class="hero-unit" style="height: 340px;">
        <h2>SMS Inbox state</h2>
        <br/>
        <g:if test="${session.user.phoneAlias || session.user.phoneNumber}">
            The SMS Inbox for your Redtappe account is ready.<br/><br/>
            You can send papers to Redtappe, via SMS or MMS, from your registered phone number.<br/>
            Also, you can tag your papers using hashtags in the text content,
            and include the expense amount as part of the message text.<br/><br/><br/>
            <div class="well" style="height: 20px; font-family: monospace;">
                Redtappe spent 42.80 at dinner #travels #madrid
            </div>
        </g:if>
        <g:if test="${!session.user.phoneAlias && !session.user.phoneNumber}">
            The SMS Inbox for your Redtappe account isn't yet activated.
            You need to configure it. Follow the instructions below.<br/><br/>
            1. Authorize your Evernote account.<br/>
            2. Send an SMS to the BlueVia service number for your country including the email you registered in Redtappe.<br/><br/>
            <div class="well" style="height: 20px; font-family: monospace;">
                Redtappe your@email.com
            </div>
            3. Redtappe processes your registration SMS, activates your SMS Inbox, and notifies you via email.<br/>
            4. Thereafter, you can send expenses to Redtappe from your movil phone.<br/><br/>
            <div class="well" style="height: 20px; font-family: monospace;">
                Redtappe spent 42.80 at dinner #travels #madrid
            </div>
        </g:if>
    </div>
</body>
</html>
