package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;

public class AccountApplicationBuilder {
    private Customer customer;
    private Maybe<AccountName> name = new Nothing<AccountName>();
    private boolean allowsOverdraft = false;
    private Money deposit = new Money(110);
    private AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(10), new Money(0));

    public AccountApplicationBuilder(Bank bank) {
        customer = bank.newCustomer(new CustomerApplicationBuilder().build());
    }

    public AccountApplicationBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public AccountApplicationBuilder allowingOverdraft() {
        this.allowsOverdraft = true;
        return this;
    }

    public AccountApplicationBuilder notAllowingOverdraft() {
        this.allowsOverdraft = false;
        return this;
    }

    public AccountApplicationBuilder deposit(Money deposit) {
        this.deposit = deposit;
        return this;
    }

    public AccountApplicationBuilder accountType(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountApplication build() {
        return new AccountApplication(customer, name, allowsOverdraft, accountType, deposit);
    }

    public AccountApplicationBuilder rate(Percentage percentage) {
        accountType.setInterestRate(percentage);
        return this;
    }
}
