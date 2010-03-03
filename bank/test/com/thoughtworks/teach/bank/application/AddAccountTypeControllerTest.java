package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class AddAccountTypeControllerTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final Controller controller = new AddAccountTypeController(bank);
    private final Map<String, String> params = new HashMap<String, String>();

    public void testShouldAddAccountTypeToBank() {
        AccountType accountType = new AccountType(new AccountTypeName("Junior"), new Percentage(0.01), new Money(0));
        params.put("accountTypeName", "Junior");
        params.put("interestRate", "0.01");

        controller.execute(params);

        assertTrue(bank.getAccountTypes().contains(accountType));
    }

    public void testShouldRedirectToAccountTypesView() {
        params.put("accountTypeName", "Junior");
        params.put("interestRate", "0.01");

        Redirect actual = controller.execute(params);
        Redirect expected = new Redirect(AccountTypesView.class);
        assertEquals(expected, actual);
    }

    public void testShouldThrowExceptionIfAccountTypeNameNotPresent() {
        params.put("accountTypeName", "");
        params.put("interestRate", "0.01");

        try {
            controller.execute(params);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Account Type Name must be entered.", e.getMessage());
        }
    }

    public void testShouldThrowExceptionIfInterestRateNotPresent() {
        params.put("accountTypeName", "Junior");
        params.put("interestRate", "");

        try {
            controller.execute(params);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Interest Rate must be entered.", e.getMessage());
        }
    }

    public void testShouldThrowExceptionIfInterestRateNotValid() {
        params.put("accountTypeName", "Junior");
        params.put("interestRate", "a.0");

        try {
            controller.execute(params);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
