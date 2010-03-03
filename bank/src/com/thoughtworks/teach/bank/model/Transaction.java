package com.thoughtworks.teach.bank.model;

import org.joda.time.DateTime;

public class Transaction {
    private final Money money;
    private final DateTime dateTime;

    public Transaction(Money money, DateTime dateTime) {
        this.money = money;
        this.dateTime = dateTime;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Transaction)) {
            return false;
        }
        Transaction otherTransaction = (Transaction) obj;
        return money.equals(otherTransaction.money) && dateTime.equals(otherTransaction.dateTime);
    }

    public String toString() {
        return getDate() + ", " + getTime() + ", " + getAmount();
    }

    public Money getAmount() {
        return money;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getTime() {
        return dateTime.toString("HH:mm");
    }

    public String getDate() {
        return dateTime.toString("dd-MM-yyyy");
    }

    public String getCreditedAmount() {
        return isDebit() ? "" : getAmount().toString();
    }

    public String getDebitedAmount() {
        return isDebit() ? getAmount().toString() : "";
    }

    private boolean isDebit() {
        return getAmount().lessThan(new Money(0));
    }
}
