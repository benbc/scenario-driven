<html>
    <head>
        <title>Found Customer Accounts</title>
    </head>
    <body>
        <h2>Found Customer Accounts</h2>
        <table border="1" cellpadding="10">
            <tr>
                <td><b>Name</b></td>
                <td><b>Nickname</b></td>
                <td><b>Address</b></td>
                <td><b>Accounts</b></td>
            </tr>
                 $customers:{<tr>
                                <td>$it.name$</td>
                                <td>$it.nickName$</td>
                                <td>$it.address$</td>
                                <td>
                                    <table id="accountsTable">
                                        $it.accounts:{ account |
                                            <tr valign="baseline">
                                                <td>$account$</td>
                                                <td><a href="/AccountServices?account=$account$">Account Services</a></td>
                                            </tr>
                                        }$
                                    </table>
                                 </td>
                             </tr>}$
        </table>
    </body>
</html>
