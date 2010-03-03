<html>
    <head>
        <title>Cashier's homepage</title>
    </head>
    <body>
        <h1>Cashier's homepage</h1>
        <h2>Find Account</h2>
        <form action="findAccount" method="post">
            Account Number: <input type="text" name="account"/>
            <input type="submit" value="Find account"/>
        </form>
        <form action="findCustomerAccountByName" method="post">
            Customer Name: <input type="text" name="customer"/>
            <input type="submit" value="Find Customer Accounts"/>
        </form>
    </body>
</html>