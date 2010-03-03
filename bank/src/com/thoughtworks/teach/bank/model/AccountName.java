package com.thoughtworks.teach.bank.model;

public class AccountName {
    private String accountName;

    public AccountName(String accountName) {
        this.accountName = accountName;
    }

    public boolean equals(Object other) {
        if (!(other instanceof AccountName)) {
            return false;
        }
        AccountName otherAccountName = (AccountName) other;
        return this.accountName.equals(otherAccountName.accountName);
    }

    public String toString() {
        return accountName;
    }
}
