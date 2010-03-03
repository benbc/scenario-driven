package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class PhoneNumberTest extends TestCase {

    public void testToStringShouldGetPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("02023456778");
        String number = phoneNumber.toString();
        assertEquals("020 2345 6778", number);
    }

    public void testEqualsShouldReturnTrueForSamePhoneNumber() {
        PhoneNumber phoneNumber1 = new PhoneNumber("02023456778");
        PhoneNumber phoneNumber2 = new PhoneNumber("02023456778");
        assertTrue(phoneNumber1.equals(phoneNumber2));
    }

    public void testEqualsShouldReturnFalseForDifferentPhoneNumber() {
        PhoneNumber phoneNumber1 = new PhoneNumber("02023456778");
        PhoneNumber phoneNumber2 = new PhoneNumber("02023456789");
        assertFalse(phoneNumber1.equals(phoneNumber2));
    }

    public void testThatValidPhoneNumberAcceptsValidInputString() {
        assertTrue(PhoneNumber.validString("01234123451"));
    }

    public void testThatValidPhoneNumberRejectsInvalidInputString() {
        assertFalse(PhoneNumber.validString("strawberry123"));
    }

    public void testShouldDisplayPhoneNumberInLondonFormat() {
        PhoneNumber phoneNumber = new PhoneNumber("02023456778");
        assertEquals("020 2345 6778", phoneNumber.toString());
    }

    public void testShouldDisplayPhoneNumberInStandardFormat() {
        PhoneNumber phoneNumber = new PhoneNumber("01379871180");
        assertEquals("01379 871180", phoneNumber.toString());
    }

    public void testThatConstructorThrowsExceptionForInvalidInputString() {
        try {
            new PhoneNumber("strawberry");
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
    public void testThatPhoneNumbersWithDifferentSpacingAreEqualToEachOther() {
        PhoneNumber phoneNumber = new PhoneNumber(" 0134 788 123 ");
        PhoneNumber phoneNumber2 = new PhoneNumber("0134788123");
        assertTrue(phoneNumber.equals(phoneNumber2));
    }

    public void testThatPhoneNumberBeginsWithZero() {
        assertFalse(PhoneNumber.validString("1234"));
    }

    public void testShouldCheckPhoneNumberIsNotMoreThanElevenDigits() {
        try {
            new PhoneNumber("0134 7883 1234 ");
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

     public void testShouldCheckPhoneNumberIsNotLessThanTenDigits() {
        try {
            new PhoneNumber("0134 7883 1 ");
            fail();
        } catch (IllegalArgumentException e) {
        }
    }
}
