package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CustomerAccountDetailsViewTest extends TestCase {

    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final CustomerAccountDetailsView view = new CustomerAccountDetailsView(bank);
    private final Customer customer;
    private final Account account;

    public CustomerAccountDetailsViewTest() {
        CustomerApplication application = new CustomerApplicationBuilder().build();
        customer = bank.newCustomer(application);
        account = bank.openAccount(new AccountApplicationBuilder(bank).build());
    }

    public void testShouldLookUpCustomerFromNickNameAndPutInTemplateParameters() {
        requestParams.put("customer", customer.getNickName().toString());
        requestParams.put("account", account.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(customer, templateParams.get("customer"));
    }

    public void testParamsContainsStatement() {
        requestParams.put("customer", customer.getNickName().toString());
        requestParams.put("account", account.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(account.getStatement(), templateParams.get("statement"));
    }
}
