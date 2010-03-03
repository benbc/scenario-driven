package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CloseAccountControllerTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final Bank bank = new Bank(repository, new CustomerRepository(), new SystemClock());
    private final Map<String, String> params = new HashMap<String, String>();
    CloseAccountController closeAccountController = new CloseAccountController(bank);
    private final Customer customer;

    public CloseAccountControllerTest() {
        CustomerApplication application = new CustomerApplicationBuilder().build();
        customer = bank.newCustomer(application);
    }

    public void testShouldNotDeleteAccountifBalanceisNotZero() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        account.deposit(new Money(100));
        String accountNumber = account.number().toString();
        params.put("account", accountNumber);
        params.put("customer", customer.getNickName().toString());
        try {
            closeAccountController.execute(params);
        }
        catch (RuntimeException e) {
        }
        assertEquals(account, bank.findAccount(account.number()).force());
    }

    public void testShouldRedirectToGenericFailureViewifBalanceisNotZero() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        account.deposit(new Money(100));
        String accountNumber = account.number().toString();
        params.put("account", accountNumber);
        params.put("customer", customer.getNickName().toString());
        try {
            closeAccountController.execute(params);
            fail();
        }
        catch (RuntimeException e) {
        }
    }

    public void testShouldDeleteAccountifBalanceisZero() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        String accountNumber = account.number().toString();
        account.withdraw(new Money(110));
        params.put("account", accountNumber);
        params.put("customer", customer.getNickName().toString());
        closeAccountController.execute(params);
        assertEquals(null, customer.account(AccountNumber.fromString(accountNumber)));
    }
}
