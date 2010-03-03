package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import junit.framework.TestCase;
import org.joda.time.DateTime;

public class MinimumBalancePolicyTest extends TestCase {
    private DateTime time;
    private Maybe<Account> fromAccount;

    public void setUp() {
        time = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        fromAccount = new Just<Account>(new AccountBuilder().deposit(new Money(2000)).build());
    }

    public void testShouldFailIfTryToWithdrawAmountGreaterThanMinimumBalance() {
        Transfer transfer = new Transfer(fromAccount, null, new Money(1900), time, false);
        MinimumBalancePolicy minimumBalancePolicy = new MinimumBalancePolicy(new Money(200));
        Outcome outcome = minimumBalancePolicy.isFulFilled(transfer);
        assertFalse(outcome.isSuccessful());
    }

    public void testFailingPolicyHasErrorMessage() {
        Transfer transfer = new Transfer(fromAccount, null, new Money(1900), time, false);
        Money minBalance = new Money(200);
        MinimumBalancePolicy minimumBalancePolicy = new MinimumBalancePolicy(minBalance);
        Outcome outcome = minimumBalancePolicy.isFulFilled(transfer);
        assertEquals("The balance for account " + fromAccount.force() + " must not fall below " + minBalance,
                outcome.errorMessage());
    }

    public void testCanWithdrawAmountDownToMinimumBalance() {
        Transfer transfer = new Transfer(fromAccount, null, new Money(1800), time, false);
        MinimumBalancePolicy minimumBalancePolicy = new MinimumBalancePolicy(new Money(200));
        Outcome outcome = minimumBalancePolicy.isFulFilled(transfer);
        assertTrue(outcome.isSuccessful());
    }

    public void testTransferBringingBalanceAboveMinimumShouldPass() {
        Transfer transfer = new Transfer(fromAccount, null, new Money(1700), time, false);
        MinimumBalancePolicy minimumBalancePolicy = new MinimumBalancePolicy(new Money(200));
        Outcome outcome = minimumBalancePolicy.isFulFilled(transfer);
        assertTrue(outcome.isSuccessful());
    }
}
