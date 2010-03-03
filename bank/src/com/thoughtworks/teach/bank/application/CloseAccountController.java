package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class CloseAccountController implements Controller {
    private final AccountFinder accountFinder;

    public CloseAccountController(Bank bank) {
        accountFinder = new AccountFinder(bank);
    }

    public Redirect execute(Map<String, String> params) {
        Account account = accountFinder.find(params, "account");
        Customer customer = account.getOwner();
        account.close();
        return new Redirect(CustomerDetailsView.class, name("customer").value(customer.getNickName()));
    }
}
