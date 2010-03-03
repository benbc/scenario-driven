package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Money;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class MakeDepositController implements Controller {

    private final AccountFinder accountFinder;

    public MakeDepositController(Bank bank) {
        accountFinder = new AccountFinder(bank);
    }

    public Redirect execute(Map<String, String> parameters) {
        Account account = accountFinder.find(parameters, "account");
        Money amount = amountFrom(parameters);
        account.deposit(amount);

        return new Redirect(AccountServicesView.class, name("account").value(account.number()));
    }

     private Money amountFrom(Map<String, String> parameters) {
        return Money.fromString(parameters.get("amount"));
    }

}
