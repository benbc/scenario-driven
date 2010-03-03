package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class AccountBuilder {

    private Customer owner = new CustomerBuilder().build();
    private AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(10.0), new Money(0));
    private boolean unarrangedOverdraft = false;
    private Money initialDeposit = new Money(0);
    private Maybe<AccountName> name = new Nothing<AccountName>();
    private Policy policy = new OnlinePaymentLimitPolicy(new Money(1000));
    private Money minBalance = new Money(0);
    private Clock clock = new TestClock(new DateTime());

    public Account build() {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(policy);
        if (!minBalance.equals(new Money(0))) {
            policies.add(new MinimumBalancePolicy(minBalance));
        }
        Account account = new Account(owner, name, unarrangedOverdraft, accountType, new CompositePolicy(policies),
                clock);
        account.deposit(initialDeposit);
        return account;
    }

    public AccountBuilder customer(Customer owner) {
        this.owner = owner;
        return this;
    }

    public AccountBuilder interestRate(AccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public AccountBuilder allowUnarrangedOverdraft(boolean unarrangedOverdraft) {
        this.unarrangedOverdraft = unarrangedOverdraft;
        return this;
    }

    public AccountBuilder deposit(Money initalDeposit) {
        this.initialDeposit = initalDeposit;
        return this;
    }

    public AccountBuilder name(String name) {
        this.name = new Just<AccountName>(new AccountName(name));
        return this;
    }

    public AccountBuilder noName() {
        this.name = new Nothing<AccountName>();
        return this;
    }

    public AccountBuilder minBalance(Money minBalance) {
        this.minBalance = minBalance;
        return this;
    }

    public AccountBuilder withZeroBalance() {
        initialDeposit = new Money(0);
        return this;
    }

    public AccountBuilder clock(TestClock testClock) {
        this.clock = testClock;
        return this;
    }

    public AccountBuilder policy(Policy policy) {
        this.policy =  policy;
        return this;
    }
}
