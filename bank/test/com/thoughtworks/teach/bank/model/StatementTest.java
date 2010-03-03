package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class StatementTest extends TestCase {

    public void testStatementReturnsTransaction() {
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(new Money(100, 55), new DateTime()));
        transactions.add(new Transaction(new Money(-2, -55), new DateTime()));
        transactions.add(new Transaction(new Money(0, 55), new DateTime()));
        Statement statement = new Statement(transactions);
        assertEquals(transactions, statement.getTransactions());
    }
}
