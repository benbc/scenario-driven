package com.thoughtworks.teach.bank.model;

public class AlwaysSucceedsPolicy implements Policy{

    public Outcome isFulFilled(Transfer transfer) {
        return new Outcome(Status.Successful, "");
    }
}
