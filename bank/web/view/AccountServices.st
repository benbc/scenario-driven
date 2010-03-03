<html>
<head>
    <title>Account Services</title>
</head>
<body>
<h2>Account Details</h2>
<table border="1" cellpadding="10">
    <tr>
        <td><b>Account Number</b></td>
        <td><b>Name</b></td>
        <td><b>Nickname</b></td>
        <td><b>Address</b></td>
    </tr>

    <tr>
        <td>$account$</td>
        <td>$account.owner.name$</td>
        <td>$account.owner.nickName$</td>
        <td>$account.owner.address$</td>
    </tr>

</table>
<br/>
<form action="makeDeposit" method="post">
    Amount: <input type="text" name="amount"/>
    <input type="hidden" name="account" value="$account$"/>
    <input type="submit" value="Make deposit"/>
</form>

<form action="makeWithdrawal" method="post">
    Amount: <input type="text" name="amount"/>
    <input type="hidden" name="account" value="$account$"/>
    <input type="submit" value="Make withdrawal"/>
</form>
</body>
</html>