package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class FoundAccountViewTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final FoundAccountView view = new FoundAccountView(bank);
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
    private final Account account = new AccountBuilder().customer(customer).build();
    private final AccountNumber number = account.number();

    public void testShouldAddFoundAccountToTemplateParams() {
        requestParams.put("account", number.toString());
        requestParams.put("customer", account.getOwner().getNickName().toString());

        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(account, templateParams.get("account"));
    }
}
