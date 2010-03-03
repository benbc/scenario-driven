package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class CustomerApplicationTest extends TestCase {
    private final Name name = new Name("Sid");
    private final Address address = new Address("21 Fish St");
    private final Postcode postcode = new Postcode("WC1 7XY") ;
    private final NickName nickName = new NickName("sid");
    private final PhoneNumber phoneNumber = new PhoneNumber("02012345678");

    public void testShouldGiveName() {
        CustomerApplication application = new CustomerApplicationBuilder().name(name).build();
        assertEquals(name, application.name());
    }

    public void testShouldGiveAddress() {
        CustomerApplication application = new CustomerApplicationBuilder().address(address).build();
        assertEquals(address, application.address());
    }

    public void testShouldGiveNickname() {
        CustomerApplication application = new CustomerApplicationBuilder().nickname(nickName).build();
        assertEquals(nickName, application.nickname());
    }

    public void testShouldGivePhoneNumber() {
        CustomerApplication application = new CustomerApplicationBuilder().phoneNumber(phoneNumber).build();
        assertEquals(phoneNumber, application.phoneNumber());
    }

    public void testShouldGivePostcode() {
        CustomerApplication application = new CustomerApplicationBuilder().postcode(postcode).build();
        assertEquals(postcode, application.postcode());
    }

    public void testShouldNotAllowNameToBeNull() {
        try {
            new CustomerApplication(null, address, postcode, nickName, phoneNumber);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowAddressToBeNull() {
        try {
            new CustomerApplication(name, null, postcode, nickName, phoneNumber);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowPostcodeToBeNull() {
        try {
            new CustomerApplication(name, address, null, nickName, phoneNumber);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowNicknameToBeNull() {
        try {
            new CustomerApplication(name, address, postcode, null, phoneNumber);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowPhoneNumberToBeNull() {
        try {
            new CustomerApplication(name, address, postcode, nickName, null);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
