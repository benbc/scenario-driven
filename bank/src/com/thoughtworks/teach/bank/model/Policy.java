package com.thoughtworks.teach.bank.model;

public interface Policy {
    Outcome isFulFilled(Transfer transfer);
}
