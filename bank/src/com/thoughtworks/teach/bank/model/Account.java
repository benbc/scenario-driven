package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final AccountNumber number = new AccountNumber();
    private final Customer owner;
    private final Maybe<AccountName> accountName;
    private final AccountType accountType;
    private final Policy policy;
    private final List<Transaction> transactions = new ArrayList<Transaction>();
    private final Clock clock;
    private final boolean overdraftAllowed;

    public Account(Customer owner, Maybe<AccountName> accountName, boolean overdraftAllowed, AccountType accountType,
                   Policy policy, Clock clock) {
        this.clock = clock;
        this.owner = owner;
        this.accountName = accountName;
        this.accountType = accountType;
        this.overdraftAllowed = overdraftAllowed;
        this.policy = policy;

        owner.addAccount(this);
    }

    public Money getBalance() {
        Money balance = new Money(0);
        for (Transaction t : transactions) {
            balance = balance.plus(t.getAmount());
        }
        return balance;
    }

    public void deposit(Money amount) {
        transactions.add(new Transaction(amount, clock.getDateTime()));
    }

    public Outcome withdraw(Money amount) {
        return transfer(amount, new Nothing<Account>(), false);
    }

    public Outcome transfer(Money requestedAmount, Account target, boolean isOnline) {
        return transfer(requestedAmount, new Just<Account>(target), isOnline);
    }

    public Customer getOwner() {
        return owner;
    }

    public AccountNumber number() {
        return number;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Maybe<AccountName> getName() {
        return accountName;
    }

    public Money getMinBalance() {
        return accountType.getMinimumBalance();
    }

    public String toString() {
        return number.toString();
    }

    private Outcome transfer(Money amount, Maybe<Account> target, boolean online) {
        Outcome outcome = checkPolicies(new Transfer(new Just<Account>(this), target, amount, clock.getDateTime(), online));
        if (!outcome.isSuccessful()) {
            return outcome;
        }
        removeFromThisAccount(amount);
        addToThatAccount(target, amount);
        return outcome;
    }

    private Outcome checkPolicies(Transfer transfer) {
        return policy.isFulFilled(transfer);
    }

    private void removeFromThisAccount(Money amount) {
        Money negativeAmount = new Money(0).minus(amount);
        transactions.add(new Transaction(negativeAmount, clock.getDateTime()));
    }

    private void addToThatAccount(Maybe<Account> target, Money amount) {
        if (target.hasValue()) {
            Account targetAccount = target.force();
            targetAccount.deposit(amount);
        }
    }

    public boolean getOverdraftAllowed() {
        return overdraftAllowed;
    }

    public void close() {
        if (!getBalance().equals(new Money(0))) {
            throw new IllegalStateException("Account " + this + " has " + getBalance() + " in it so it cannot be closed.");
        }
        owner.removeAccount(this);
    }

    public Statement getStatement() {
        return new Statement(transactions);
    }

    public Money calculateInterest() {
        return getBalance().getPercentage(accountType.getDailyRate());
    }
}
