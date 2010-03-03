package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;
import org.joda.time.DateTime;

public class TransactionTest extends TestCase {
    public void testToStringShouldPrintDebitedTransactionDetails() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Transaction transaction = new Transaction(new Money(50), dateTime);
        assertEquals(("01-02-2008, 12:00, £50.00"), transaction.toString());
    }

    public void testToStringShouldPrintCreditedTransactionDetails() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Transaction transaction = new Transaction(new Money(-50), dateTime);
        assertEquals(("01-02-2008, 12:00, -£50.00"), transaction.toString());
    }

    public void testGetTheAmountFromATransaction() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Transaction transaction = new Transaction(new Money(50), dateTime);
        Money amount = transaction.getAmount();
        assertTrue(new Money(50).equals(amount));
    }

    public void testGetTheDateTimeFromATransaction() {//done
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Transaction transaction = new Transaction(new Money(50), dateTime);
        DateTime date = transaction.getDateTime();
        assertTrue(date.equals(dateTime));
    }

    public void testTimeAmHoursAreDisplayedCorrectely() {//done
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transaction transaction = new Transaction(new Money(50), dateTime);
        assertEquals("12:55", transaction.getTime());
    }

    public void testDateIsCorrectelyFormated() { //done
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transaction transaction = new Transaction(new Money(50), dateTime);
        assertEquals("01-02-2008", transaction.getDate());
    }

    public void testCreditsShouldHaveEmptyDebitedAmount() {//done
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transaction transaction = new Transaction(new Money(-50), dateTime);
        assertEquals("", transaction.getCreditedAmount());
        assertEquals("-£50.00", transaction.getDebitedAmount());
    }

    public void testDebitsShouldHaveEmptyCreditedAmount() { //done
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transaction transaction = new Transaction(new Money(50), dateTime);
        assertEquals("£50.00", transaction.getCreditedAmount());
        assertEquals("", transaction.getDebitedAmount());
    }
}
