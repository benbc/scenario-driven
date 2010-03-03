<html>
    <head>
        <title>Make Payment</title>
    </head>
    <body>
     <h2>Payments</h2>
        <form action="existingPaymentTemplates" method="post">
            Existing Payment Templates:
            <select name="templateId" onchange="this.form.submit();">
                <option>Choose template...</option>
                $fromCustomer.paymentTemplates:{<option value="$it.id$">$it.toAccount.owner$ $it.amount$</option>}$
            </select>
            <input type="hidden" name="fromCustomer"  value="$fromCustomer.nickName$" />
        </form>
        <form action="makePayment" method="post">
            <input type="hidden" name="fromCustomer" value="$fromCustomer.nickName$"/>
            <table>
                <tr>
                    <td>From:</td>
                    <td>
                        <select name="fromAccount">
                            $fromCustomer.accounts:{<option value="$it$">$it$</option>}$
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>To Account Number:</td>
                    <td>
                        <input type="text" name="toAccount" value="$toAccount$"/>
                        <a href="/SearchNames?fromCustomer=$fromCustomer.nickName$">Search</a>
                    </td>
                </tr>
                <tr>
                    <td>Amount:</td>
                    <td><input type="text" name="amount" value="$amount$"/></td>
                </tr>
                <tr>
                    <td>Payment Date (dd-mm-yyyy):</td>
                    <td><input type="text" name="paymentDate"/></td>
                </tr>
            </table>
            <input type="checkbox" name="createTemplate" value=1/>check to create a template<br/><br/>
            <input type="submit" value="Make Payment"/>
        </form>
        <a href="/CustomerHomepage?customer=$fromCustomer.nickName$">Back to homepage</a>
    </body>
</html>