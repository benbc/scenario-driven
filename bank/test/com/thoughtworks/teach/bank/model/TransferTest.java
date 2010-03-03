package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import junit.framework.TestCase;
import org.joda.time.DateTime;

public class TransferTest extends TestCase {
    private Maybe<Account> fromAccount;
    private Maybe<Account> toAccount;
    private DateTime time;

    public void setUp() {
        fromAccount = new Just<Account>(new AccountBuilder().build());
        toAccount = new Just<Account>(new AccountBuilder().build());
        time = new DateTime(2008, 2, 1, 12, 0, 0, 0);
    }

    public void testShouldReturnFromAccount() {
        Money amount = new Money(1000);
        Transfer transfer = new Transfer(fromAccount, toAccount, amount, time, true);
        assertEquals(fromAccount.force(), transfer.getFromAccount().force());
    }

    public void testShouldReturnToAccount() {
        Money amount = new Money(1000);
        Transfer transfer = new Transfer(fromAccount, toAccount, amount, time, true);
        assertEquals(toAccount.force(), transfer.getToAccount().force());
    }

    public void testShouldReturnAmountToTransfer() {
        Money amount = new Money(1000);
        Transfer transfer = new Transfer(fromAccount, toAccount, amount, time, true);
        assertEquals(amount, transfer.getAmountToTransfer());
    }

    public void testGetTheDateTimeFromATransaction() {
        DateTime dateTime = new DateTime(2008, 3, 5, 12, 46, 55, 23);
        Transfer transfer = new Transfer(fromAccount, new Nothing<Account>(), new Money(50), dateTime, false);
        assertEquals(dateTime, transfer.getDateTime());
    }

    public void testTimeAmHoursAreDisplayedCorrectely() {
        DateTime dateTime = new DateTime(2008, 2, 1, 4, 55, 1, 5);
        Transfer transfer = new Transfer(fromAccount, new Nothing<Account>(), new Money(50), dateTime, false);
        assertEquals("04:55", transfer.getTime());
    }

    public void testTimePmHoursAreDisplayedCorrectely() {
        DateTime dateTime = new DateTime(2008, 2, 1, 16, 55, 1, 5);
        Transfer transfer = new Transfer(fromAccount, new Nothing<Account>(), new Money(50), dateTime, false);
        assertEquals("16:55", transfer.getTime());
    }

    public void testDateIsCorrectelyFormated() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transfer transfer = new Transfer(fromAccount, new Nothing<Account>(), new Money(50), dateTime, false);
        assertEquals("01-02-2008", transfer.getDate());
    }

    public void testCreditsShouldHaveEmptyDebitedAmount() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transfer transfer = new Transfer(fromAccount, new Nothing<Account>(), new Money(50), dateTime, false);
        assertEquals("", transfer.getCreditedAmount());
        assertEquals("-£50.00", transfer.getDebitedAmount());
    }

    public void testDebitsShouldHaveEmptyCreditedAmount() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 55, 1, 5);
        Transfer transfer = new Transfer(new Nothing<Account>(),toAccount, new Money(50), dateTime, false);
        assertEquals("£50.00", transfer.getCreditedAmount());
        assertEquals("", transfer.getDebitedAmount());
    }
}
