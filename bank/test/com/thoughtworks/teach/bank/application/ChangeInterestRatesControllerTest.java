package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class ChangeInterestRatesControllerTest extends TestCase {
    private Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    ChangeInterestRatesController changeInterestRates = new ChangeInterestRatesController(bank);
    private final Map<String, String> params = new HashMap<String, String>();

    public void testShouldBAbleToModifyInterestRateForAParticularAccountType() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(2.2), new Money(0));
        bank.addAccountType(accountType);
        params.put("accountType", accountType.getAccountTypeName().toString());
        params.put("interestRate", "2.5");
        Redirect redirect = changeInterestRates.execute(params);
        assertEquals(new Redirect(AdminHomepageView.class), redirect);

    }

    public void testShouldReturnAccountTypeFromParams() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(5.25), new Money(0));
        bank.addAccountType(accountType);
        params.put("accountType", new AccountTypeName("Current").toString());
        assertEquals(accountType, changeInterestRates.getAccountTypeFrom(params));
    }

    public void testShouldReturnInterestRateFromParams() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(5.25), new Money(0));
        bank.addAccountType(accountType);
        String temp = accountType.getInterestRate().toString();
        params.put("interestRate", temp.substring(0, temp.length() - 1));
        assertEquals(accountType.getInterestRate(), changeInterestRates.getInterestRateFrom(params));
    }

    public void testRedirectToFailureViewWhenTheInterestRateIsAnString() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(2.2), new Money(0));
        bank.addAccountType(accountType);
        params.put("accountType", new AccountTypeName("Current").toString());
        params.put("interestRate", "snjs");
        try {
            changeInterestRates.execute(params);
            fail();
        }
        catch (IllegalArgumentException e) {
        }
    }
}
