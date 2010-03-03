package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class AccountTypeTest extends TestCase {
    public void testInterestRatesWithSameValueAreEqual() {

        assertEquals(new AccountType(new AccountTypeName("Current"), new Percentage(5.25), new Money(0)), new AccountType(new AccountTypeName("Current"),
                new Percentage(5.25), new Money(0)));
    }

    public void testInterestRatesWithDifferentInterestRatesAreNotEqual() {
        assertFalse(new AccountType(new AccountTypeName("Current"), new Percentage(5.25), new Money(0)).equals(new AccountType(new AccountTypeName("Current"),
                new Percentage(5.15), new Money(0))));
    }

    public void testInterestRatesWithDifferentInterestTypeAreNotEqual() {
        assertFalse(new AccountType(new AccountTypeName("Current"), new Percentage(5.25), new Money(0)).equals(new AccountType(new AccountTypeName("Savings"),
                new Percentage(5.25), new Money(0))));
    }

    public void testToStringGivesCorrectFormat() {
        assertEquals(new AccountTypeName("Current") + " (8.25%)", new AccountType(new AccountTypeName("Current"), new Percentage(8.25), new Money(0)).toString());
    }

    public void testShouldReturnInterestTypeName() {
        assertEquals(new AccountTypeName("Current"), new AccountType(new AccountTypeName("Current"), new Percentage(8.25), new Money(0)).getAccountTypeName());
    }

    public void testReturnsInterestRateAsPercentage() {
        assertEquals(new Percentage(2.3), new AccountType(new AccountTypeName("Current"), new Percentage(2.3), new Money(0)).getInterestRate());
    }

    public void testShouldBeAbleToSetANewInterestRateToAAccountType() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(2.3), new Money(0));
        accountType.setInterestRate(new Percentage(3.6));
        assertEquals(new Percentage(3.6), accountType.getInterestRate());
    }

    public void testAccountTypeDailyPercentageChanges() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(16.6), new Money(0));
        assertEquals(new Percentage(0.045479452054794534), accountType.getDailyRate());
    }

    public void testAccountTypeHasAMinimumBalance() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(16.6), new Money(100));
        assertEquals(new Money(100), accountType.getMinimumBalance());
    }
    public void testAccountTypeCanHaveAMinimumBalanceChanged() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(16.6), new Money(100));
        accountType.changeMinimumBalance(new Money(50));
        assertEquals(new Money(50), accountType.getMinimumBalance() );
    }
}
