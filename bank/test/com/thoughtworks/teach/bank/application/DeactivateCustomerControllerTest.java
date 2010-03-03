package com.thoughtworks.teach.bank.application;

import junit.framework.TestCase;
import com.thoughtworks.teach.bank.model.*;

import java.util.Map;
import java.util.HashMap;

public class DeactivateCustomerControllerTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final CustomerRepository deactivatedRepository = new CustomerRepository();
    private final Bank bank = new Bank(repository, deactivatedRepository, new SystemClock());
    private final DeactivateCustomerController deactivateCustomerController = new DeactivateCustomerController(bank);
    private final Map<String, String> params = new HashMap<String, String>();

    public void testDeactivateSingleCustomer() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        params.put("deactivateCustomer-1", customer.getNickName().toString());
        deactivateCustomerController.execute(params);
        assertTrue(deactivatedRepository.contains(customer));
    }

    public void testAttemptToDeactivateCustomerWithAccountShouldThrowException() {
        Customer customer1 = bank.newCustomer(new CustomerApplicationBuilder().build());
        Customer customer2 = bank.newCustomer(new CustomerApplicationBuilder().build());
        customer2.addAccount(new AccountBuilder().build());
        params.put("deactivateCustomer-1", customer1.getNickName().toString());
        params.put("deactivateCustomer-2", customer2.getNickName().toString());
        try {
            deactivateCustomerController.execute(params);
            fail();
        }
        catch (IllegalStateException e) {
            // expected
        }
    }
}
