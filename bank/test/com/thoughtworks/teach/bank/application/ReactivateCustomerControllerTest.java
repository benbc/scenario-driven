package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class ReactivateCustomerControllerTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final CustomerRepository deactivatedRepository = new CustomerRepository();
    private final Bank bank = new Bank(repository, deactivatedRepository, new SystemClock());
    private final ReactivateCustomerController reactivateCustomerController = new ReactivateCustomerController(bank);
    private final Map<String, String> params = new HashMap<String, String>();

    public void testReactivateSingleCustomer() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(customer);
        params.put("reactivateCustomer-1", customer.getNickName().toString());
        reactivateCustomerController.execute(params);
        assertFalse(deactivatedRepository.contains(customer));
        assertTrue(repository.contains(customer));
    }

    public void testThatReactivateActiveCustomerThrowsAnException() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        params.put("reactivateCustomer-1", customer.getNickName().toString());
        try {
            reactivateCustomerController.execute(params);
            fail();
        }
        catch (RuntimeException e) {
            //expected
        }
    }
    public void testExecuteShouldRedirectToCustomerListView() {
        Redirect expected = new Redirect(CustomerListView.class);
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(customer);
        params.put("reactivateCustomer-1", customer.getNickName().toString());
        Redirect found = reactivateCustomerController.execute(params);
        assertEquals(expected, found);
    }
}
