package com.thoughtworks.teach.view;

import com.thoughtworks.teach.bank.application.BankRegistry;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.view.pageDrivers.PersonalDetailsPage;
import com.thoughtworks.teach.web.WebServer;
import junit.framework.TestCase;

public class PersonalDetailsPageTest extends TestCase {
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

    public void testTextFieldsDisplayCorrectData() {
        PersonalDetailsPage page = new PersonalDetailsPage(testCustomer);
        assertEquals(testCustomer.getName().toString(), page.nameField().text());
        assertEquals(testCustomer.getNickName().toString(), page.nicknameField().text());
        assertEquals(testCustomer.getAddress().toString(), page.addressField().text());
        assertEquals(testCustomer.getPostcode().toString(), page.postcodeField().text());
        assertEquals(testCustomer.getPhoneNumber().toString(), page.phoneNumberField().text());
    }

    public void testNameAndNicknameAreReadonly() {
        PersonalDetailsPage page = new PersonalDetailsPage(testCustomer);
        assertTrue(page.nameField().isReadonly());
        assertTrue(page.nicknameField().isReadonly());
    }

    public void testCanEditAddress(){
//        PersonalDetailsPage page = new PersonalDetailsPage(testCustomer);
//        String newAddress = "14B, New Street Name";
//        page.changeAddressFieldTo(newAddress);
//        page.saveChanges();
//        assertEquals(newAddress, page.addressField().text());
    }

    public void testCanNavigateBackToHomepage(){
//        PersonalDetailsPage page = new PersonalDetailsPage(testCustomer);
//        page.goBackToHomepage();
    }
}
