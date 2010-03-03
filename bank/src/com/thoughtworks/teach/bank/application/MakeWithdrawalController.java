package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Money;
import com.thoughtworks.teach.bank.model.Outcome;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class MakeWithdrawalController implements Controller {
    private Bank bank;

    public MakeWithdrawalController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        String accountParam = parameters.get("account");
        String amountParam = parameters.get("amount");
        AccountFinder accountFinder = new AccountFinder(bank);
        Account account = accountFinder.find(parameters, "account");
        Outcome result = account.withdraw(Money.fromString(amountParam));
        if (!(result.isSuccessful())) {
            throw new RuntimeException("You have insufficient funds in your account");
        }
        return new Redirect(AccountServicesView.class, name("account").value(accountParam));
    }


}
