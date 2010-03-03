package com.thoughtworks.teach.bank.model;

public class Address {
    private final String address;

    public Address(String address) {
        if ((address == null) || (address.isEmpty())) {
            throw new IllegalArgumentException("Address must not be null or empty.");
        }
        this.address = address;
    }

    public boolean equals(Object that) {
        return that instanceof Address
                && ((Address) that).address.equals(this.address);
    }

    public String toString() {
        return address;
    }
}
