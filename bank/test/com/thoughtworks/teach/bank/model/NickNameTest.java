package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class NickNameTest extends TestCase {
    public void testThatSameNickNamesAreEqual() {
        assertEquals(new NickName("jenny"), new NickName("jenny"));
    }

    public void testThatDifferentNickNamesAreNotEqual() {
        assertFalse(new NickName("jenny").equals(new NickName("isabella")));
    }

    public void testThatToStringReturnsNickName() {
        assertEquals("jenny", new NickName("jenny").toString());
    }

    public void testThatNickNameCannotBeNull() {
        try {
            new NickName(null);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("NickName cannot be null", e.getMessage());
        }
    }

    public void testShouldNotAllowNickNameToBeNonAlphaNumeric() {
        try {
            new NickName("$^&*");
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Nicknames must consist of just numbers and lowercase letters. This is not on: $^&*", e.getMessage());
        }
    }

    public void testShouldNotAllowNickNameToBeUpperCase() {
        try {
            new NickName("Ben");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Nicknames must consist of just numbers and lowercase letters. This is not on: Ben", e.getMessage());
        }
    }
}
