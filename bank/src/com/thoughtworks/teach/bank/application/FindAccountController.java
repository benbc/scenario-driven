package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.web.Redirect;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;

import java.util.Map;

public class FindAccountController implements Controller {
    private final AccountFinder finder;

    public FindAccountController(Bank bank) {
        finder = new AccountFinder(bank);
    }

    public Redirect execute(Map<String, String> params) {
        Account account = finder.find(params, "account");
        return new Redirect(FoundAccountView.class, name("account").value(account.number()));
    }
}
