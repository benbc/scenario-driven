package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.View;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CustomerViewTest extends TestCase {

    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final NickName nickName = new NickName("ben");
    private final Customer customer;

    public CustomerViewTest() {
        customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickName).build());
    }

    public void testParametersArePassedToCustomerView() {
        View customerView = new CustomerView(bank){};
        requestParams.put("customer", customer.getNickName().toString());
        Map<String, Object> templateParams = customerView.process(requestParams);
        assertEquals(1, templateParams.size());
        assertEquals(customer, templateParams.get("customer"));
    }
}
