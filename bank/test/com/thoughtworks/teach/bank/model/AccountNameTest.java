package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class AccountNameTest extends TestCase {
       
    public void testTwoAccountNamesAreEqual() {
        AccountName accountName = new AccountName("Savings");
        AccountName anotherAccountName = new AccountName("Savings");
        assertEquals(accountName,anotherAccountName);
    }
}
