package com.thoughtworks.teach.bank.model;

public class AccountType {
    private final AccountTypeName accountTypeName;
    private Percentage rate;
    private Money minBalance;

    public AccountType(AccountTypeName name, Percentage rate, Money minBalance) {
        this.accountTypeName = name;
        this.rate = rate;
        this.minBalance = minBalance;
    }

    public AccountTypeName getAccountTypeName() {
        return accountTypeName;
    }

    public void setInterestRate(Percentage interestRate) {
        this.rate = interestRate;
    }

    public Percentage getInterestRate() {
        return rate;
    }

    public String toString() {
        return accountTypeName + " (" + rate + ")";
    }

    public boolean equals(Object other) {
        if (!(other instanceof AccountType)) {
            return false;
        }
        AccountType otherAccountType = (AccountType) other;
        return this.rate.equals(otherAccountType.rate)
                && this.accountTypeName.equals(otherAccountType.accountTypeName);
    }

    public int hashCode() {
        int result;
        result = accountTypeName.hashCode();
        result = 31 * result + rate.hashCode();
        return result;
    }

    public Percentage getDailyRate() {
        return new Percentage(Math.exp(Math.log(rate.toDouble() / 365)));
    }

    public Money getMinimumBalance() {
        return minBalance;
    }

    public void changeMinimumBalance(Money minBalance) {
        this.minBalance = minBalance;
    }
}
