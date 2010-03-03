package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class PostcodeTest extends TestCase {
    public void testToStringShouldGetPostcode() {
        Postcode postcode = new Postcode("EC1E 4DD");
        String code = postcode.toString();
        assertEquals("EC1E 4DD", code);
    }

    public void testEqualsShouldReturnTrueForSamePostcode() {
        Postcode postcode1 = new Postcode("EC1E 4DD");
        Postcode postcode2 = new Postcode("EC1E 4DD");
        assertTrue(postcode1.equals(postcode2));
    }

    public void testEqualsShouldReturnFalseForDifferentPostcode() {
        Postcode postcode1 = new Postcode("EC1E 4DD");
        Postcode postcode2 = new Postcode("EC1E 4DE");
        assertFalse(postcode1.equals(postcode2));
    }

    public void testThatValidStringAcceptsValidInputString(){
        assertTrue(Postcode.validString("EC1E 4DD"));
    }

    public void testThatValidStringRejectsInputStringComposedOfIllegalCharacters(){
        assertFalse(Postcode.validString("!£$%^&"));
    }

    public void testThatValidStringRejectsExcessivelyShortInputStringComposedOfLegalCharacters(){
        assertFalse(Postcode.validString("EC1"));
    }

    public void testThatValidStringRejectsExcessivelyLongInputStringComposedOfLegalCharacters(){
        assertFalse(Postcode.validString("EC1A 4XXY"));
    }

    public void testThatValidStringRejectsInputWithLeadingNumericCharacter(){
        assertFalse(Postcode.validString("4EC1 4XY"));
    }

    public void testThatConstructorThrowsExceptionForInvalidInputString(){
        try {
               new Postcode("strawberry");
               fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

       public void testShouldNotAllowNullPostcode() {
        try {
            new Postcode("");
            fail();
        } catch (RuntimeException e) {
            // expected
        }
    }
}
