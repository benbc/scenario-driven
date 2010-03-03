<html>
<head>
    <title>Personal Details</title>
</head>
<body>
<h2>Personal Details</h2>

<form action="personalDetails" method="post">
    <table>
        <tr><td>Name:</td>
         <td><input name="name" value="$customer.name$" readonly="true"/></td>
        </tr>
        <tr><td>Nickname:&nbsp;&nbsp;</td>
            <td><input name="customer" type="text" value="$customer.nickName$" readonly="true"/></td>
        </tr>
        <tr><td>Address: </td>
            <td><textarea name="address" rows="4" cols="20">$customer.address$</textarea></td>
        </tr>
        <tr><td>Postcode:  </td>
            <td><input name="postcode" type="text" size="10" value="$customer.postcode$" /></td>
        </tr>
        <tr><td>Phone:</td>
        <td><input name="phoneNumber" type="text" size="20" value="$customer.phoneNumber$"/></td></tr>
    </table>
    <input type="submit" value ="Confirm"/>
</form>
<a href="/CustomerHomepage?customer=$customer.nickName$">Back to homepage</a>
</body>
</html>