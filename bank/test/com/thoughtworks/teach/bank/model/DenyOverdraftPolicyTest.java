package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import junit.framework.TestCase;
import org.joda.time.DateTime;

public class DenyOverdraftPolicyTest extends TestCase {
    private DateTime time;
    private Maybe<Account> zeroBalanceAccount;

    public void setUp() {
        time = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        zeroBalanceAccount = new Just<Account>(new AccountBuilder().withZeroBalance().build());
    }

    public void testShouldNotAllowTransactionThatMakesAccountOverdrawn() {
        Transfer transfer = new Transfer(zeroBalanceAccount, new Nothing<Account>(), new Money(100), time, false);
        Outcome outcome = new DenyOverdraftPolicy().isFulFilled(transfer);
        assertFalse(outcome.isSuccessful());
    }

    public void testShouldGiveCorrectErrorMessageOnFailure() {
        Transfer transfer = new Transfer(zeroBalanceAccount, new Nothing<Account>(), new Money(100), time, false);
        Outcome outcome = new DenyOverdraftPolicy().isFulFilled(transfer);
        assertEquals("Account " + zeroBalanceAccount.force() + " does not have an overdraft facility.", outcome.errorMessage());
    }

    public void testShouldAllowTransactionThatDoesNotMakeAccountOverdrawn() {
        Maybe<Account> account = new Just<Account>(new AccountBuilder().deposit(new Money(10000)).build());
        Transfer transfer = new Transfer(account, new Nothing<Account>(), new Money(100), time, false);
        Outcome outcome = new DenyOverdraftPolicy().isFulFilled(transfer);
        assertTrue(outcome.isSuccessful());
    }
}
