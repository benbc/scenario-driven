package com.thoughtworks.teach.view;

import com.thoughtworks.teach.bank.application.BankRegistry;
import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.bank.model.Money;
import com.thoughtworks.teach.view.pageDrivers.AccountDetailsPage;
import com.thoughtworks.teach.view.pageDrivers.CustomerHomepage;
import com.thoughtworks.teach.view.pageDrivers.MakePaymentPage;
import com.thoughtworks.teach.view.pageDrivers.PersonalDetailsPage;
import com.thoughtworks.teach.view.tester.HtmlPageTester;
import com.thoughtworks.teach.web.WebServer;
import junit.framework.TestCase;

import java.util.Iterator;

public class CustomerHomepageTest extends TestCase {
    private WebServer server;
    private Customer testCustomer;
    public Account firstAccount;
    public Account secondAccount;

    public void setUp() {
        BankTestFixture testFixture = new BankTestFixture();
        server = new WebServer(new BankRegistry(), testFixture, 8088);
        server.run();
        testCustomer = testFixture.testCustomer();
        Iterator<Account> accountIterator = testCustomer.getAccounts().iterator();
        firstAccount = accountIterator.next();
        secondAccount = accountIterator.next();
    }

    public void tearDown() {
        server.stop();
    }

    public void testCustomersFullNameIsDisplayed() {
        CustomerHomepage page = new CustomerHomepage(testCustomer);
        assertTrue(page.displays(testCustomer.getName() + "'s homepage"));
    }

    public void testAccountDetailsLinkLinksToAccountDetailsPage() {
        CustomerHomepage page = new CustomerHomepage(testCustomer);
        HtmlPageTester accountDetailsPage = page.clickLink(AccountDetailsPage.mainUrl);
        assertEquals(new AccountDetailsPage(testCustomer, testCustomer.getAccounts().iterator().next()).currentUrl(), accountDetailsPage.currentUrl());
    }

    public void testMakePaymentLinkLinksToMakePaymentPage() {
        CustomerHomepage page = new CustomerHomepage(testCustomer);
        HtmlPageTester makePaymentsPage = page.clickLink(MakePaymentPage.mainUrl);
        assertEquals(new MakePaymentPage(testCustomer).currentUrl(), makePaymentsPage.currentUrl());
    }

    public void testPersonalDetailsLinkLinksToPersonalDetailsPage() {
        CustomerHomepage page = new CustomerHomepage(testCustomer);
        HtmlPageTester personalDetailsPage = page.clickLink(PersonalDetailsPage.mainUrl);
        assertEquals(new PersonalDetailsPage(testCustomer).currentUrl(), personalDetailsPage.currentUrl());
    }

    public void testCanMakeATransfer() {
        CustomerHomepage page = new CustomerHomepage(testCustomer);
        Money firstAccountBalance = firstAccount.getBalance();
        Money secondAccountBalance = secondAccount.getBalance();
        Money transferAmount = new Money(444,55);
        assertTrue(page.displays("Transfers"));
        page.selectTransferFrom(secondAccount);
        page.selectTransferTo(this.firstAccount);
        page.transferAmount("444.55");
        page.makeTransfer();
        //refresh page
        page = new CustomerHomepage(testCustomer);
        assertTrue(page.displays(firstAccountBalance.plus(transferAmount).toString()));
        assertTrue(page.displays(secondAccountBalance.minus(transferAmount).toString()));
    }

    public void testListOfAccountsIsDisplayed() {
        CustomerHomepage page = new CustomerHomepage(testCustomer);
        assertTrue(page.displays("Accounts"));
        assertTrue(page.displays(firstAccount.number().toString()));
        assertTrue(page.displays(secondAccount.number().toString()));
        assertTrue(page.displays(firstAccount.getBalance().toString()));
        assertTrue(page.displays(secondAccount.getBalance().toString()));
    }
}
