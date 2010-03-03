package com.thoughtworks.teach.view;

import com.thoughtworks.teach.bank.application.BankRegistry;
import com.thoughtworks.teach.view.pageDrivers.*;
import com.thoughtworks.teach.view.tester.HtmlPageTester;
import com.thoughtworks.teach.web.WebServer;
import junit.framework.TestCase;

public class AdminHomepageTest extends TestCase {
    private WebServer server;

    public void setUp() {
        BankTestFixture testFixture = new BankTestFixture();
        server = new WebServer(new BankRegistry(), testFixture, 8088);
        server.run();
    }

    public void tearDown() {
        server.stop();
    }

    public void testTitleContainsCustomersFullName() {
        AdminHomepage page = new AdminHomepage();
        assertTrue(page.displays("Account Administrator Homepage"));
    }

    public void testAddNewCustomerLinksToAddCustomerPage() {
        AdminHomepage adminHomepage = new AdminHomepage();
        HtmlPageTester resultPage = adminHomepage.clickLink(AddNewCustomerPage.mainUrl);
        assertEquals(new AddNewCustomerPage().currentUrl(), resultPage.currentUrl());
    }

    public void testCreateNewAccountLinkLinksToCreateAccountPage() {
        AdminHomepage adminHomepage = new AdminHomepage();
        HtmlPageTester resultPage = adminHomepage.clickLink(CreateAccountPage.mainUrl);
        assertEquals(new CreateAccountPage().currentUrl(), resultPage.currentUrl());
    }

    public void testViewAllCustomersLinkLinksToCustomerListPage() {
        AdminHomepage adminHomepage = new AdminHomepage();
        HtmlPageTester resultPage = adminHomepage.clickLink(CustomerListPage.mainUrl);
        assertEquals(new CustomerListPage().currentUrl(), resultPage.currentUrl());
    }

    public void testChangeInterestRatesLinkLinksToChangeInterestsRatesPage() {
        AdminHomepage adminHomepage = new AdminHomepage();
        HtmlPageTester resultPage = adminHomepage.clickLink(AccountTypesPage.mainUrl);
        assertEquals(new AccountTypesPage().currentUrl(), resultPage.currentUrl());
    }

}
