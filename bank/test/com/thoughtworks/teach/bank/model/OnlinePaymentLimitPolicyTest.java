package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import junit.framework.TestCase;
import org.joda.time.DateTime;

public class OnlinePaymentLimitPolicyTest extends TestCase {
    private Maybe<Account> bobsAccount1;
    private Maybe<Account> bobsAccount2;
    private Maybe<Account> aliceAccount;
    private DateTime time;



    public OnlinePaymentLimitPolicyTest() {
        Customer bob = new CustomerBuilder().build();
        bobsAccount1 = new Just<Account>(new AccountBuilder().customer(bob).build());
        bobsAccount2 = new Just<Account>(new AccountBuilder().customer(bob).build());
        Customer alice = new CustomerBuilder().name(new Name("alice")).build();
        aliceAccount = new Just<Account>(new AccountBuilder().customer(alice).build());
        time = new DateTime(2008, 2, 1, 12, 0, 0, 0);
    }

    public void testCheckIfICanTransferMoneyBetweenSameCustomerAccounts() {
        OnlinePaymentLimitPolicy transferPolicy = new OnlinePaymentLimitPolicy(new Money(1000));
        Transfer transfer = new Transfer(bobsAccount1, bobsAccount2, new Money(1200), time, false);
        Outcome outcome = transferPolicy.isFulFilled(transfer);
        assertTrue(outcome.isSuccessful());
    }

    public void testCannotTransferMoneyExceedingLimitBetweenTwoDifferentCustomerAccounts() {
        OnlinePaymentLimitPolicy transferPolicy = new OnlinePaymentLimitPolicy(new Money(1000));
        Transfer transfer = new Transfer(bobsAccount1, aliceAccount, new Money(1200), time, true);
        Outcome outcome = transferPolicy.isFulFilled(transfer);
        assertFalse(outcome.isSuccessful());
    }

    public void testFailingLimitPolicyHasErrorMessage() {
        Money limit = new Money(1000);
        OnlinePaymentLimitPolicy transferPolicy = new OnlinePaymentLimitPolicy(limit);
        Transfer transfer = new Transfer(bobsAccount1, aliceAccount, new Money(1200), time, true);
        Outcome outcome = transferPolicy.isFulFilled(transfer);
        assertEquals("Online payments of more than " + limit + " are not allowed.", outcome.errorMessage());
    }

    public void testCanTransferMoneyLessThanLimitBetweenTwoDifferentCustomerAccounts() {
        OnlinePaymentLimitPolicy transferPolicy = new OnlinePaymentLimitPolicy(new Money(1000));
        Transfer transfer = new Transfer(bobsAccount1, aliceAccount, new Money(120), time, true);
        Outcome outcome = transferPolicy.isFulFilled(transfer);
        assertTrue(outcome.isSuccessful());
    }
}
