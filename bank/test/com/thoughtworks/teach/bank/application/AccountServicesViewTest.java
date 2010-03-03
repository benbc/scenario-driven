package com.thoughtworks.teach.bank.application;

import junit.framework.TestCase;
import com.thoughtworks.teach.bank.model.*;

import java.util.Map;
import java.util.HashMap;

public class AccountServicesViewTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final AccountServicesView view = new AccountServicesView(bank);
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
    private final Account account = new AccountBuilder().customer(customer).build();

    public void testShouldAddAccountToTemplateParams() {
        requestParams.put("account", account.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(account, templateParams.get("account"));
    }
}
