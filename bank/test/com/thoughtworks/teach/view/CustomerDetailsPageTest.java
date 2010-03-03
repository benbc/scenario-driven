package com.thoughtworks.teach.view;

import com.thoughtworks.teach.bank.application.BankRegistry;
import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.view.pageDrivers.CustomerDetailsPage;
import com.thoughtworks.teach.web.WebServer;
import junit.framework.TestCase;

import java.util.Iterator;

public class CustomerDetailsPageTest extends TestCase {
    private WebServer server;

    private Customer testCustomer;

    public void setUp() {
        BankTestFixture testFixture = new BankTestFixture();
        server = new WebServer(new BankRegistry(), testFixture, 8088);
        server.run();
        testCustomer = testFixture.testCustomer();
    }

    public void tearDown() {
        server.stop();
    }

    public void testTitleContainsCustomersFullName() {
        CustomerDetailsPage page = new CustomerDetailsPage(testCustomer);
        assertTrue(page.displays(testCustomer.getName() + "'s Details"));
    }

    public void testDisplaysCustomerDetailsTableCorrectly() {
        CustomerDetailsPage page = new CustomerDetailsPage(testCustomer);
        assertTrue(page.displays("Nickname", "Address", "Postcode", "Phone number"));
        assertTrue(page.displays(testCustomer.getNickName().toString()));
        assertTrue(page.displays(testCustomer.getAddress().toString()));
        assertTrue(page.displays(testCustomer.getPostcode().toString()));
        assertTrue(page.displays(testCustomer.getPhoneNumber().toString()));
    }

    public void testDisplaysAccountsTableCorrectly() {
        CustomerDetailsPage page = new CustomerDetailsPage(testCustomer);
        Iterator<Account> accountIterator = testCustomer.getAccounts().iterator();
        Account firstAccount = accountIterator.next();
        Account secondAccount = accountIterator.next();
        assertTrue(page.displays("Account", "Balance", "Interest Rate", "Minimum Balance", "Allowed Unarranged Overdraft"));
        assertTrue(page.displays(firstAccount.number().toString()));
        System.out.println(firstAccount.getBalance().toString());
        page.dump();
        assertTrue(page.displays(firstAccount.getBalance().toString()));
        assertTrue(page.displays(firstAccount.getAccountType().toString()));
        assertTrue(page.displays(firstAccount.getMinBalance().toString()));
        assertTrue(page.displays(secondAccount.number().toString()));
        assertTrue(page.displays(secondAccount.getBalance().toString()));
        assertTrue(page.displays(secondAccount.getAccountType().toString()));
        assertTrue(page.displays(secondAccount.getMinBalance().toString()));
    }
}
