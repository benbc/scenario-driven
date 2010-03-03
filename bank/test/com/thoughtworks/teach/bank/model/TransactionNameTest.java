package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class TransactionNameTest extends TestCase {

    public void testTwoEqualTransactionNamesAreEqual() {

        TransactionName firstTransaction = new TransactionName("Transfer");
        TransactionName secondTransaction = new TransactionName("Transfer");
        assertEquals(firstTransaction, secondTransaction);
    }

    public void testReturnsItsName(){
        String name = "aCoolName";
        assertEquals(name, new TransactionName(name).toString());
    }

}
