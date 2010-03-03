package com.thoughtworks.teach.bank.model;

public class TransactionName {
    private final String name;

    public TransactionName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TransactionName)) {
            return false;
        }
        TransactionName otherTransactionName = (TransactionName) obj;
        return this.name.equals(otherTransactionName.name);
    }

    public int hashCode() {
        return name.hashCode();
    }
}
