package com.thoughtworks.teach.bank.model;


import org.joda.time.LocalDate;

public class Payment {

    private Account toAccount;
    private Account fromAccount;
    private Money amount;
    private LocalDate date;

    public Payment(Account toAccount, Account fromAccount, Money amount, LocalDate date) {
        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.date = date;
    }

    public String toString() {
        return (toAccount.toString() + " " + fromAccount.toString() + " " + amount.toString()
                + " " + date.toString());
    }

    public Boolean settlePayment(LocalDate currentDate) {
        if (date.isAfter(currentDate)) {
            return false;
        }

        fromAccount.transfer(amount, toAccount, true);
        return true;
    }
}
