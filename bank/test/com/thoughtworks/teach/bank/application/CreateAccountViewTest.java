package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountViewTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final CreateAccountView view = new CreateAccountView(bank);
    private final Map<String, String> requestParams = new HashMap<String, String>();

    public CreateAccountViewTest() {
        bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.addAccountType(new AccountType(new AccountTypeName("Current"), new Percentage(1.0), new Money(0)));
    }

    public void testShouldAddAllCustomersToTemplateParams() {
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(bank.customers(), templateParams.get("customers"));
    }

    public void testShouldAddAllAccountTypesToTemplateParams() {
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(bank.getAccountTypes(), templateParams.get("accountTypes"));
    }
}
