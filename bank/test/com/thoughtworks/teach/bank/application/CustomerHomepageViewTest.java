package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CustomerHomepageViewTest extends TestCase {
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final NickName nickName = new NickName("ben");
    private final CustomerHomepageView view = new CustomerHomepageView(bank);
    private final Customer customer;

    public CustomerHomepageViewTest() {
        customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickName).build());
    }

    public void testShouldPutFromCustomerNickNameInTemplateParameters() {
        requestParams.put("customer", nickName.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(customer, templateParams.get("customer"));
    }

    public void testShouldThrowAnExceptionIfCustomerCannotBeFound() {
        requestParams.put("customer", "a name that no one has");
        try {
            view.process(requestParams);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
