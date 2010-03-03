package com.thoughtworks.teach.bank.model;

public class Outcome {
    private Status status;
    private String errorMessage;

    public Outcome(Status status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public boolean isSuccessful() {
        return status.equals(Status.Successful);
    }

    public String errorMessage() {
        return errorMessage;
    }
}
