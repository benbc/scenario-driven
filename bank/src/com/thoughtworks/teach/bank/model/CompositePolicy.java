package com.thoughtworks.teach.bank.model;

import java.util.List;

public class CompositePolicy implements Policy {
    private final List<Policy> policies;

    public CompositePolicy(List<Policy> policies) {
        this.policies = policies;
    }

    public Outcome isFulFilled(Transfer transfer) {
        for (Policy p : policies) {
            Outcome outcome = p.isFulFilled(transfer);
            if (!outcome.isSuccessful()) {
                return outcome;
            }
        }
        return new Outcome(Status.Successful, "");
    }
}
