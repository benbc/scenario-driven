<html>
    <head>
        <title>$customer.name$'s Account Details</title>
    </head>
    <body>
        <h1>$customer.name$'s Account Details</h1>

        <form action="findAccountDetails" method="post">
            Select Account: <select name="account">
            $customer.accounts:{
            <option value="$it$">$it$ - $it.name$</option>
            }$
        </select>
            <input name="nickName" readonly="true" value=$customer.nickName$ type=hidden>
            <input type="submit" value="Show Details"/>
        </form>
        
        <h2>Details for Account $account$ - $account.name$</h2>
        <table border="1">
            <tr>
                <td>Interest rate</td>
                <td>$account.accountType.interestRate$</td>
            </tr>
            <tr>
                <td>Balance</td>
                <td>$account.balance$</td>     
            </tr>
            <tr>
                <td>Minimum Balance</td>
                <td>$account.minBalance$</td>
            </tr>
            <tr>
                <td>Allowed Unarranged Overdraft</td>
                <td>$account.overdraftAllowed$</td>
            </tr>
        </table>

        <h2>Statement</h2>
        <table id="statementTable" border="1">
            <tr>
                <td>Date</td>
                <td>Time</td>
                <td>Debit</td>
                <td>Credit</td>
            </tr>
            <tr>$statement.transactions :
                {
                <td align="right">$it.date$</td>
                <td>$it.time$</td>
                <td>$it.debitedAmount$</td>
                <td>$it.creditedAmount$</td>
            </tr>
            }$
        </table>
        <br/>
        <a href="/CustomerHomepage?customer=$customer.nickName$">Back to homepage</a>
    </body>
</html>