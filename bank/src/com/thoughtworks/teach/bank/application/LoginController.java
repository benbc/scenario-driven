package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class LoginController implements Controller {
    private final CustomerFinder finder;

    public LoginController(Bank bank) {
        finder = new CustomerFinder(bank);
    }

    public Redirect execute(Map<String, String> parameters) {
        Customer customer = finder.find(parameters, "customer");
        return new Redirect(CustomerHomepageView.class, name("customer").value(customer.getNickName()));
    }
}
