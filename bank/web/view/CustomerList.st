<html>
<head>
    <title>List of Customers</title>
</head>
<body>
    <h1>List of Customers</h1>
    <form action="deactivateCustomer" method="post">
          $customers:{ <input type="checkbox" name="deactivateCustomer$i$" value="$it.nickName$"/>
                    <a href="/CustomerDetails?customer=$it.nickName$"> $it$</a><br/>}$
          <br/><input type="submit" value="Deactivate"/>
        <br/>
    </form>
    <h1>List of Deactivated Customers</h1>
    <form action="reactivateCustomer" method="post">
          $deactivatedCustomers:{ <input type="checkbox" name="reactivateCustomer$i$" value="$it.nickName$"/>
        <a href="/CustomerDetails?customer=$it.nickName$"> $it$</a><br/>}$
        <br/><input type="submit" value="Reactivate"/>
    </form>
</body>
</html>
