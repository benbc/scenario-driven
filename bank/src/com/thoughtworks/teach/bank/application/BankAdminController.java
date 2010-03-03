package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Money;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class BankAdminController implements Controller {
    private final Bank bank;

    public BankAdminController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        bank.setOnlinePaymentLimit(limitFrom(parameters));
        return new Redirect(BankAdminView.class);
    }

    private Money limitFrom(Map<String, String> parameters) {
        if(parameters.get("onlinePaymentAmountLimit").equals("")){
            throw new RuntimeException("Online Payment Limit should not be blank");
        }
        return Money.fromString(parameters.get("onlinePaymentAmountLimit"));
    }
}
