package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class MakeTransferController implements Controller {
    private final AccountFinder finder;

    public MakeTransferController(Bank bank) {
        finder = new AccountFinder(bank);
    }

    public Redirect execute(Map<String, String> parameters) {
        Account fromAccount = finder.find(parameters, "from");
        Account toAccount = finder.find(parameters, "to");
        Money amountRequested = amountFrom(parameters);
        transfer(fromAccount, toAccount, amountRequested);
        return new Redirect(CustomerHomepageView.class, name("customer").value(fromAccount.getOwner().getNickName()));
    }

    private void transfer(Account fromAccount, Account toAccount, Money amountRequested) {
        Outcome outcome = fromAccount.transfer(amountRequested, toAccount, true);
        if (!outcome.isSuccessful()) {
            throw new RuntimeException(outcome.errorMessage());
        }
    }

    private Money amountFrom(Map<String, String> parameters) {
        return Money.fromString(parameters.get("amount"));
    }
}
