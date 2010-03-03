package com.thoughtworks.teach.view.pageDrivers;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Customer;

public class AccountDetailsPage extends DefaultPage {
    public static String mainUrl = "/CustomerAccountDetails";

    public AccountDetailsPage(Customer customer, Account account) {
        super(mainUrl + "?customer=" + customer.getNickName() + "&account=" + account);
    }

}
