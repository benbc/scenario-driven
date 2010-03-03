package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class AccountTypeNameTest extends TestCase {
    public void testTwoEqualAccountTypeNamesAreEqual() {
        AccountTypeName accountType1 = new AccountTypeName("Junior");
        AccountTypeName accountType2 = new AccountTypeName("Junior");
        assertTrue(accountType1.equals(accountType2));
    }

    public void testTwoUnequalAccountTypeNamesAreNotEqual() {
        AccountTypeName accountType1 = new AccountTypeName("Junior");
        AccountTypeName accountType2 = new AccountTypeName("OAP");
        assertFalse(accountType1.equals(accountType2));
    }

    public void testAccountTypeNameCanBeDisplayed() {
        assertTrue("Junior".equals(new AccountTypeName("Junior").toString()));
    }
}
