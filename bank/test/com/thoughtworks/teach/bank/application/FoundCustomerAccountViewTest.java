package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoundCustomerAccountViewTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final FoundCustomerAccountView view = new FoundCustomerAccountView(bank);
    private final Map<String, String> requestParams = new HashMap<String, String>();

    public FoundCustomerAccountViewTest() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        new AccountBuilder().customer(customer).build();
    }

    public void testShouldAddCustomerToTemplateParams() {
        List<Customer> customers = new CustomerRepository().findByName(new Name("Isabella Degen"));

        requestParams.put("customer",customers.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(customers, templateParams.get("customers"));
    }
}
