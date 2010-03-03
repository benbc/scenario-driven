package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class AddressTest extends TestCase {

    public void testAddressesShouldBeEqualForTheSameAddress() {
        assertEquals(new Address("22 The Street,London"),
                new Address("22 The Street,London"));
    }

    public void testAddressesShouldBeUnequalForDifferentAddresses() {
        assertFalse(new Address("22 The Street,London").equals
                (new Address("12 The Road,London")));
    }

    public void testToStringShouldReturnTheAddress() {
        assertEquals(("22 The Street,London"),
                new Address("22 The Street,London").toString());
    }

    public void testShouldNotAllowNullAddress() {
        try {
            new Address(null);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowEmptyAddress() {
        try {
            new Address("");
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }


}
