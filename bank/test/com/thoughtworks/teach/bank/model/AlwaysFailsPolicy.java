package com.thoughtworks.teach.bank.model;

public class AlwaysFailsPolicy implements Policy{
    private final String message;

    public AlwaysFailsPolicy(String message) {
        this.message = message;
    }

    public AlwaysFailsPolicy() {
        message = "Always fails policy";
    }

    public Outcome isFulFilled(Transfer transfer) {
        return new Outcome(Status.Failed, message);
    }
}
