package com.thoughtworks.teach.view;

import com.thoughtworks.teach.bank.application.BankRegistry;
import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.bank.model.Transaction;
import com.thoughtworks.teach.view.pageDrivers.AccountDetailsPage;
import com.thoughtworks.teach.web.WebServer;
import junit.framework.TestCase;

public class AccountDetailsPageTest extends TestCase {
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

    public void testShouldDesplayTitleCorrectely() {
        AccountDetailsPage page = new AccountDetailsPage(testCustomer, testCustomer.getAccounts().iterator().next());
        assertTrue(page.displays(testCustomer.getName() + "'s Account Details"));
    }

    public void testShouldHaveStatementSection() {
        AccountDetailsPage page = new AccountDetailsPage(testCustomer, testCustomer.getAccounts().iterator().next());
        assertTrue(page.displays("Statement"));
    }

    public void testShouldDisplayStatementTableCorrectly() {
        Account account = testCustomer.getAccounts().iterator().next();
        Transaction transaction = account.getStatement().getTransactions().iterator().next();
        AccountDetailsPage page = new AccountDetailsPage(testCustomer, testCustomer.getAccounts().iterator().next());
        assertTrue(page.displays("Date", "Time", "Debit", "Credit"));
        assertTrue(page.displays(transaction.getDate()));
        assertTrue(page.displays(transaction.getTime()));
        assertTrue(page.displays(transaction.getDebitedAmount()));
        assertTrue(page.displays(transaction.getCreditedAmount()));
    }

}
