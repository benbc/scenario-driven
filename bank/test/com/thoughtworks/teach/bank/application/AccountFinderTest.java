package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;

public class AccountFinderTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final AccountFinder finder = new AccountFinder(bank);
    private final HashMap<String, String> params = new HashMap<String, String>();

    public void testShouldThrowExceptionIfParameterIsNotFound() {
        try {
            finder.find(params, "missingParam");
            fail();
        } catch (RuntimeException e) {
            // expected
        }
    }

    public void testShouldThrowExceptionIfAccountIsNotFound() {
        params.put("account", new AccountNumber().toString());
        try {
            finder.find(params, "account");
            fail();
        } catch (RuntimeException e) {
            // expected
        }
    }

    public void testShouldReturnAccountIfTheyExist() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).build());
        params.put("account", account.number().toString());

        Account actual = finder.find(params, "account");
        assertEquals(account, actual);
    }
}