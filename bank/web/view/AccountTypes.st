<html>
    <head>
        <title>Account Types</title>
    </head>

    <body>
        <form action="changeInterestRates" method="post">
            <tr><td>Account Type:</td><td> <select name="accountType">
                    $accountTypes:{<option value="$it.accountTypeName$">$it$</option>}$
                </select></td></tr><br/>
            Change Interest Rate To: <input name="interestRate" type="text" value=""/><br/>
            <input type="submit" value="Change Interest Rate"/>
        </form>

        <form action="addAccountType" method="post">
            Account Type Name : <input type="text" name="accountTypeName"/><br/>
            Interest Rate: <input name="interestRate" type="text"/><br/>
            <input type="submit" value="Add Account Type"/>
        </form>
    </body>
</html>