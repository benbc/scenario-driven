package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class AddAccountTypeController implements Controller {
    private final Bank bank;

    public AddAccountTypeController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        bank.addAccountType(accountTypeFrom(parameters));
        return new Redirect(AccountTypesView.class);
    }

    private AccountType accountTypeFrom(Map<String, String> parameters) {
        AccountTypeName name = nameFrom(parameters);
        Percentage rate = rateFrom(parameters);
        return new AccountType(name, rate, new Money(0));
    }

    private Percentage rateFrom(Map<String, String> parameters) {
        String rate = parameters.get("interestRate");
        if (rate.equals("")) {
            throw new IllegalArgumentException("Interest Rate must be entered.");
        }
        return Percentage.fromString(rate);
    }

    private AccountTypeName nameFrom(Map<String, String> parameters) {
        String name = parameters.get("accountTypeName");
        if (name.equals("")) {
            throw new IllegalArgumentException("Account Type Name must be entered.");
        }
        return new AccountTypeName(name);
    }
}
