package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CustomerDetailsViewTest extends TestCase {
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final CustomerDetailsView view = new CustomerDetailsView(bank);
    private final Customer customer;

    public CustomerDetailsViewTest() {
        CustomerApplication application = new CustomerApplicationBuilder().build();
        customer = bank.newCustomer(application);
    }

    public void testShouldPutCustomerInTemplateParameters() {
        bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());

        requestParams.put("customer", customer.getNickName().toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(customer, templateParams.get("customer"));
    }
}
