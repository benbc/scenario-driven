package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MatchingCustomersViewTest extends TestCase {
    private final Map<String, String> requestParams = new HashMap<String, String>();
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final Name isabellaName = new Name("Isabella");
    private final NickName ben = new NickName("ben");
    private final NickName isabella = new NickName("isabella");
    private final Customer customer;
    private final MatchingCustomersView view = new MatchingCustomersView(bank);

    public MatchingCustomersViewTest() {
        customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(ben).build());
        bank.newCustomer(new CustomerApplicationBuilder().nickname(isabella).name(isabellaName).build());
    }

    public void testShouldPutFromCustomerInTemplateParameters() {
        requestParams.put("fromCustomer", ben.toString());
        requestParams.put("toCustomer", isabella.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(ben.toString(), templateParams.get("fromCustomer").toString());
    }

    public void testShouldPutToCustomerNameInTemplateParameters() {
        requestParams.put("fromCustomer", ben.toString());
        requestParams.put("toCustomer", isabella.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(isabellaName.toString(), templateParams.get("toCustomerName").toString());
    }

    public void testShouldThrowAnExceptionIfCustomerCannotBeFound() {
        requestParams.put("fromCustomer", "a name that no one has");
        try {
            view.process(requestParams);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldPutCustomersAccountInTemplateParameters() {
        bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        Set<Account> accounts = customer.getAccounts();
        requestParams.put("fromCustomer", ben.toString());
        requestParams.put("toCustomer", customer.getNickName().toString());
        requestParams.put("toAccounts", accounts.toString());
        Map<String, Object> templateParams = view.process(requestParams);
        assertEquals(accounts.toString(), templateParams.get("toAccounts").toString());
    }
}
