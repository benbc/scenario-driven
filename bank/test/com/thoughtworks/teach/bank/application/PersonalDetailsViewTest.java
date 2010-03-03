package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.Map;
import java.util.HashMap;

public class PersonalDetailsViewTest extends TestCase {
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final PersonalDetailsView view = new PersonalDetailsView(bank);
    Customer ourcustomer = bank.newCustomer(new CustomerApplicationBuilder().build());

    public void testShouldPutCustomerInTemplateParameters() {
        requestParams.put("customer", ourcustomer.getNickName().toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(ourcustomer, templateParams.get("customer"));
    }
}
