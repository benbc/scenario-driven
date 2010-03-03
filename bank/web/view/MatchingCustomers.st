<html>
    <head>
        <title>List of Matching Customers</title>
    </head>
    <body>
        <h4>List of Matching Customers</h4>
        <b>$toCustomerName$:</b>
                $toAccounts:{ account |
                            <ul>
                                <li>
                                   <a href="/MakePayment?toAccount=$account$&amp;fromCustomer=$fromCustomer$">
                                       $account$
                                   </a>
                                </li>
                            </ul>
                }$
    </body>
</html>