package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class CreateAccountController implements Controller {
    private final Bank bank;
    private final CustomerFinder finder;

    public CreateAccountController(Bank bank) {
        this.bank = bank;
        finder = new CustomerFinder(bank);
    }

    public Redirect execute(Map<String, String> params) {
        bank.openAccount(applicationFrom(params));
        return new Redirect(AdminHomepageView.class);
    }

    private AccountApplication applicationFrom(Map<String, String> params) {
        Customer customer = finder.find(params, "owner");
        return new AccountApplication(customer, accountNameFrom(params),
                allowUnarrangedOverdraftFrom(params), interestRateFrom(params), depositFrom(params));
    }

    private Money depositFrom(Map<String, String> params) {
        return Money.fromString(params.get("deposit"));
    }

    private boolean allowUnarrangedOverdraftFrom(Map<String, String> params) {
        return params.containsKey("allowUnarrangedOverdraft");
    }

    private AccountType interestRateFrom(Map<String, String> params) {
        return bank.findAccountType(new AccountTypeName(params.get("accountType"))).force();
    }

    private Maybe<AccountName> accountNameFrom(Map<String, String> params) {
        if (!params.containsKey("accountName") || params.get("accountName").equals("")) {
            return new Nothing<AccountName>();
        }
        return new Just<AccountName>(new AccountName(params.get("accountName")));
    }
}
