<html>
<head>
    <title>Add a new customer</title>
</head>
<body>
<h2>Add New Customer</h2>

<form action="addCustomer" method="post">
    <table>
        <tr><td>Name:</td>
            <td><input name="name" type="text"/></td>
        </tr>
        <tr><td> Nickname:</td>
            <td><input name="nickName" type="text"/></td>
        </tr>
        <tr><td>Address : </td>
            <td><textarea name="address" rows="4" cols="20"></textarea></td>
        </tr>
        <tr><td>Postcode:  </td>
            <td><input name="postcode" type="text"/></td>
        </tr>
        <tr><td>Phone number:&nbsp;&nbsp;</td>
            <td><input name="phoneNumber" type="text"/></td>
        </tr>
    </table>
    <input type="submit" value="Add Customer"/>
</form>
</body>
</html>