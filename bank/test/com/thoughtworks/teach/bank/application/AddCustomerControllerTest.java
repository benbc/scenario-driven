package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCustomerControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final AddCustomerController controller = new AddCustomerController(bank);
    private final Map<String, String> parameters = new HashMap<String, String>();
    private String name = "Ben";
    private String address = "13 Downing Street, London";
    private String postcode = "ST5 6TY";
    private String nickname = "bt1";
    private String phoneNumber = "02012345678";

    public AddCustomerControllerTest() {
        parameters.put("name", name);
        parameters.put("address", address);
        parameters.put("postcode", postcode);
        parameters.put("nickName", nickname);
        parameters.put("phoneNumber", phoneNumber);
    }

    public void testShouldAddCustomerToRepository() {
        controller.execute(parameters);
        assertTrue(customers.findByName(new Name(name)).size() >= 1);
    }

    public void testShouldRedirectToAdminHomepage() {
        Redirect redirect = controller.execute(parameters);
        assertEquals(new Redirect(AdminHomepageView.class), redirect);
    }

    public void testShouldReturnTheAddressOfTheCustomer() {
        controller.execute(parameters);
        boolean success = false;
        List<Customer> customerList = customers.findByName(new Name(name));
        Address customerAddress = new Address(address);
        for (Customer c : customerList) {

            if (customerAddress.equals(c.getAddress())) {
                success = true;
            }
        }
        assertTrue(success);
    }

    public void testShouldReturnThePostcodeOfTheCustomer() {
        controller.execute(parameters);
        boolean success = false;
        List<Customer> customerList = customers.findByName(new Name(name));
        Postcode customerPostcode = new Postcode(postcode);
        for (Customer c : customerList) {

            if (customerPostcode.equals(c.getPostcode())) {
                success = true;
            }
        }
        assertTrue(success);
    }

    public void testShouldReturnThePhoneNumberOfTheCustomer() {
        controller.execute(parameters);
        boolean success = false;
        List<Customer> customerList = customers.findByName(new Name(name));
        PhoneNumber customerPhoneNumber = new PhoneNumber(phoneNumber);
        for (Customer c : customerList) {

            if (customerPhoneNumber.equals(c.getPhoneNumber())) {
                success = true;
            }
        }
        assertTrue(success);
    }

    public void testShouldUseNickNameInParametersForCustomer() {
        controller.execute(parameters);
        Maybe<Customer> customer = customers.findByNickName(new NickName(nickname));
        assertTrue(customer.hasValue());
    }

    public void testShouldThrowExceptionIfNickNameContainsInvalidCharacters() {
        String invalidNickname = "a#";
        parameters.put("nickName", invalidNickname);
        try {
            controller.execute(parameters);
            fail();
        } catch (RuntimeException e) {
            // expected
        }
    }
}
