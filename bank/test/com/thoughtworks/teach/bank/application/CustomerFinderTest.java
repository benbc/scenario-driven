package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;

public class CustomerFinderTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final CustomerFinder finder = new CustomerFinder(bank);
    private final HashMap<String, String> params = new HashMap<String, String>();

    public void testShouldThrowExceptionIfParameterIsNotFound() {
        try {
            finder.find(params, "missingParam");
            fail();
        } catch (RuntimeException e) {
            // expected
        }
    }

    public void testShouldThrowExceptionIfCustomerIsNotFound() {
        params.put("customer", "bob");
        try {
            finder.find(params, "customer");
            fail();
        } catch (RuntimeException e) {
            // expected
        }
    }

    public void testShouldReturnCustomerIfTheyExist() {
        params.put("customer", "bob");
        Customer expected = bank.newCustomer(new CustomerApplicationBuilder().nickname("bob").build());

        Customer actual = finder.find(params, "customer");
        assertEquals(expected, actual);
    }
}
