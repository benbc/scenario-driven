package com.thoughtworks.teach.view.pageDrivers;

import com.thoughtworks.teach.bank.model.Customer;

public class MakePaymentPage extends DefaultPage {
    public static String mainUrl = "/MakePayment";

    public MakePaymentPage(Customer customer) {
        super(mainUrl + "?fromCustomer=" + customer.getNickName());
    }
}
