package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Maybe;
import org.joda.time.DateTime;

public class Transfer {
    private final Maybe<Account> fromAccount;
    private final Maybe<Account> toAccount;
    private final Money amountToBeTransferred;
    private final DateTime dateTime;
    private final boolean isOnline;

    public Transfer(Maybe<Account> fromAccount, Maybe<Account> toAccount, Money amount, DateTime dateTime, boolean isOnline) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amountToBeTransferred = amount;
        this.dateTime = dateTime;
        this.isOnline = isOnline;
    }

    public Maybe<Account> getFromAccount() {
        return fromAccount;
    }

    public Maybe<Account> getToAccount() {
        return toAccount;
    }

    public Money getAmountToTransfer() {
        return amountToBeTransferred;
    }

    public boolean isOnline() {
        return isOnline;
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
        if (!fromAccount.hasValue()) {
            return amountToBeTransferred.toString();
        }
        return "";
    }

    public String getDebitedAmount() {
        if (fromAccount.hasValue()) {
            return new Money(0).minus(amountToBeTransferred).toString();
        }
        return "";
    }
}
