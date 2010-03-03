<html>
<head>
    <title>$customer.name$'s Details</title>
</head>
<body>
<h1>$customer.name$'s Details</h1>

<table id="customerDetailsTable" border="1" cellpadding="5" cellspacing="0">
    <tr>
        <td><strong>Nickname</strong></td>
        <td><strong>Address</strong></td>
        <td><strong>Postcode</strong></td>
        <td><strong>Phone number</strong></td>
    </tr>
    <tr>
        <td>$customer.nickName$</td>
        <td>$customer.address$</td>
        <td>$customer.postcode$</td>
        <td>$customer.phoneNumber$</td>
    </tr>
</table>
<br/>
<table id="customerAccountDetailsTable" border="1" cellpadding="5" cellspacing="0">
    <tr>
        <td>&nbsp;</td>
        <td><strong>Account </strong></td>
        <td><strong>Balance </strong></td>
        <td><strong>Interest Rate </strong></td>
        <td><strong>Minimum Balance </strong></td>
        <td><strong>Allowed Unarranged Overdraft </strong></td>
    </tr>
    $customer.accounts:{
    <tr valign="CENTER">
        <td>
            <form action="closeAccount" method="post">
                <input type="hidden" name="account" value="$it$"/>
                <input type="submit" value="Close"/>
            </form>
        </td>
        <td>$it$</td>
        <td>$it.balance$</td>
        <td>$it.accountType$</td>
        <td>$it.minBalance$</td>
        <td>$it.overdraftAllowed$</td>
    </tr>
    }$
</table>
<br/>
<a href="/AdminHomepage">Back to admin homepage</a>
</body>
</html>