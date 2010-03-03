package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.AccountType;
import com.thoughtworks.teach.bank.model.AccountTypeName;
import com.thoughtworks.teach.bank.model.Percentage;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class ChangeInterestRatesController implements Controller {
    private final Bank bank;

    public ChangeInterestRatesController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> params) {
        bank.changeInterestRate(getAccountTypeFrom(params), getInterestRateFrom(params));
        return new Redirect(AdminHomepageView.class);
    }

    public AccountType getAccountTypeFrom(Map<String, String> params) {
        return bank.findAccountType(new AccountTypeName(params.get("accountType"))).force();
    }

    public Percentage getInterestRateFrom(Map<String, String> params) {
        Percentage percentage;
        try {
            percentage = new Percentage(Double.parseDouble(params.get("interestRate")));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Enter a valid Interest Rate. ");
        }
        return percentage;
    }
}
