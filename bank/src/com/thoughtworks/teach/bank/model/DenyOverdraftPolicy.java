package com.thoughtworks.teach.bank.model;

public class DenyOverdraftPolicy implements Policy {
    public Outcome isFulFilled(Transfer transfer) {
        if (transfer.getFromAccount().force().getBalance().lessThan(transfer.getAmountToTransfer())) {
            return new Outcome(Status.Failed, "Account " + transfer.getFromAccount().force() + " does not have an overdraft facility.");
        } else {
            return new Outcome(Status.Successful, "");
        }
    }
}
