<html>
    <head><title>Create an account</title></head>
    <body>
        <h1>Create an account</h1>
        <form action="createAccount" method="post">
            <table>
               <tr>
                   <td>Customer:</td>
                   <td><select name="owner">
                       $customers:{<option value="$it.nickName$">$it$</option>}$
                   </select></td>
                </tr>
                <tr>
                    <td>Account Name:</td>
                    <td><input name="accountName"type="text"/></td>
                </tr>
                <tr>
                    <td>Account Type:</td>
                    <td><select name="accountType">
                        $accountTypes:{<option value="$it.accountTypeName$">$it$</option>}$
                    </select></td>
                </tr>
                <tr>
                    <td>Deposit:</td>
                    <td><input type="text" name="deposit"/></td>
                </tr>
                <tr>
                    <td>Allow Unarranged Overdraft:</td>
                    <td><input type="checkbox"name="allowUnarrangedOverdraft" value=1/></td>
                </tr>
            </table>
            <input type="submit" value="Create"/>
        </form>
    </body>
</html>