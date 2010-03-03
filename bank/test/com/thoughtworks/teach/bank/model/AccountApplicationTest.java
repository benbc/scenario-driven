package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import junit.framework.TestCase;

public class AccountApplicationTest extends TestCase {
    private final Customer customer = new CustomerBuilder().build();
    private final Maybe<AccountName> name = new Just<AccountName>(new AccountName("my special account"));
    private final boolean allowsOverdraft = true;
    private final AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(10), new Money(0));
    private final Money deposit = new Money(10);

    public void testShouldContainCustomer() {
        assertEquals(customer, new AccountApplication(customer, name, allowsOverdraft, accountType, deposit).customer());
    }

    public void testShouldContainName() {
        assertEquals(name, new AccountApplication(customer, name, allowsOverdraft, accountType, deposit).name());
    }

    public void testShouldContainOverdraftStatus() {
        assertTrue(new AccountApplication(customer, name, allowsOverdraft, accountType, deposit).allowUnarrangedOverdraft());
    }

    public void testShouldContainAccountType() {
        assertEquals(accountType, new AccountApplication(customer, name, allowsOverdraft, accountType, deposit).accountType());
    }

    public void testShouldContainDeposit() {
        assertEquals(deposit, new AccountApplication(customer, name, allowsOverdraft, accountType, deposit).initialDeposit());
    }

    public void testShouldNotAllowNullCustomer() {
        try {
            new AccountApplication(null, name, allowsOverdraft, accountType, deposit);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowNullName() {
        try {
            new AccountApplication(customer, null, allowsOverdraft, accountType, deposit);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowNullAccountType() {
        try {
            new AccountApplication(customer, name, allowsOverdraft, null, deposit);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldNotAllowNullDeposit() {
        try {
            new AccountApplication(customer, name, allowsOverdraft, accountType, null);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
