<html>
    <head>
        <title>$customer.name$'s homepage</title>
    </head>
    <body>
        <h1>$customer.name$'s homepage</h1>

        <h2>Accounts</h2>

        <table id="accountsTable" border="1">
          <tr>
            <td><strong>Account</strong></td>
            <td><strong>Balance</strong></td>
          </tr>
           $customer.accounts:{
                      <tr>
                        <td>$it$</td>
                        <td align="right">$it.balance$</td>
                      </tr>
                     }$
        </table>

        <br/>
        <a href="/CustomerAccountDetails?customer=$customer.nickName$&account=$first(customer.accounts)$">Account details</a>
        <br/>
        <a href="/PersonalDetails?customer=$customer.nickName$">Personal details </a>
        <br/>

        <h2>Transfers</h2>
        <form action="makeTransfer" method="post">
            <table>
                <tr>
                    <td>From:</td>
                    <td>
                        <select name="from">
                            $customer.accounts:{<option value="$it$">$it$</option>}$
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>To:</td>
                    <td>
                        <select name="to">
                            $customer.accounts:{<option value="$it$">$it$</option>}$
                        </select></td>
                </tr>
                <tr><td>Amount:</td><td><input type="text" name="amount"/></td></tr>
            </table>
            <input type="submit" value="Make transfer"/>
        </form>
        <br/>
        <a href="/MakePayment?fromCustomer=$customer.nickName$">Make Payment</a>
    </body>
</html>

