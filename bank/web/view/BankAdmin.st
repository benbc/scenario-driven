<html>
    <head>
        <title>Bank Administration Page</title>
    </head>

    <body>
        <h2>Limit allowed online payment's amount</h2>
        <form action="bankAdmin" method="post">
            Max. allowed amount: <input name="onlinePaymentAmountLimit" type="text"/>
            <input type="submit" value="Submit"/>
        </form>

        <h2>Do daily processing</h2>
        <form action="dailyProcessing" method="post">
            Date (dd-mm-yyyy): <input type="text" name="processingDate"/>
            <input type="submit" value="Do daily processing"/>
        </form>

    </body>
</html>
