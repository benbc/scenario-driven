package com.thoughtworks.teach.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    List<Transaction> transactions = new ArrayList<Transaction>();

    public Statement(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Statement)) {
            return false;
        }
        Statement otherStatement = (Statement) obj;
        return transactions.equals(otherStatement.transactions);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
