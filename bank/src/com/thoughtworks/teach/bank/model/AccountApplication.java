package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Maybe;

public class AccountApplication {
    private final Customer customer;
    private final Maybe<AccountName> name;
    private final boolean allowsOverdraft;
    private final AccountType accountType;
    private final Money initialDeposit;

    public AccountApplication(Customer customer, Maybe<AccountName> name, boolean allowsOverdraft,
                              AccountType accountType, Money deposit) {
        if (customer == null || name == null || accountType == null || deposit == null) {
            throw new IllegalArgumentException("Customer, name, account type and deposit must not be null");
        }
        this.customer = customer;
        this.name = name;
        this.allowsOverdraft = allowsOverdraft;
        this.accountType = accountType;
        initialDeposit = deposit;
    }

    public Customer customer() {
        return customer;
    }

    public Maybe<AccountName> name() {
        return name;
    }

    public boolean allowUnarrangedOverdraft() {
        return allowsOverdraft;
    }

    public AccountType accountType() {
        return accountType;
    }

    public Money initialDeposit() {
        return initialDeposit;
    }
}
