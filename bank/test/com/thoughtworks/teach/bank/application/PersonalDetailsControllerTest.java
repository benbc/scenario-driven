package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class PersonalDetailsControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Map<String, String> parameters = new HashMap<String, String>();
    private final PersonalDetailsController controller = new PersonalDetailsController(bank);
    Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
    Address newAddress = new Address("22 Spring Street");
    Postcode newPostcode = new Postcode("WC1E 4XX");
    PhoneNumber newPhoneNumber = new PhoneNumber ("01379871180");

    public PersonalDetailsControllerTest() {
        parameters.put("customer", customer.getNickName().toString());
        parameters.put("address", newAddress.toString());
        parameters.put("postcode", newPostcode.toString());
        parameters.put("phoneNumber","01379871180");
    }

    public void testShouldUpdateCustomerAddress() {
        controller.execute(parameters);
        assertEquals(newAddress, customer.getAddress());
    }

    public void testShouldUpdateCustomerPhoneNumber() {
        controller.execute(parameters);
        assertEquals(newPhoneNumber, customer.getPhoneNumber());
    }

    public void testShouldUpdateCustomerPostcode() {
        controller.execute(parameters);
        assertEquals(newPostcode, customer.getPostcode());
    }
}
