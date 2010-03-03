package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomerListViewTest extends TestCase {
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final CustomerListView view = new CustomerListView(bank);

    public void testShouldPutCustomersInTemplateParameters() {
        bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.newCustomer(new CustomerApplicationBuilder().build());
        Set<Customer> customers = bank.customers();
        Map<String, Object> templateParams = view.process(requestParams);

        assertEquals(customers, templateParams.get("customers"));
    }

    public void testShouldPutDeactivatedCustomersInTemplateParameters() {
        Customer c1 = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(c1);
        Set<Customer> customers = bank.deactivatedCustomers();
        Map<String, Object> templateParams = view.process(requestParams);

        assertEquals(customers, templateParams.get("deactivatedCustomers"));
    }
}
