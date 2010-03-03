package com.thoughtworks.teach.view.pageDrivers;

import com.thoughtworks.teach.bank.model.Customer;

public class CustomerDetailsPage extends DefaultPage {
    public static String mainUrl = "/CustomerDetails";

    public CustomerDetailsPage(Customer customer) {
        super(mainUrl + "?customer=" + customer.getNickName());
    }

}
