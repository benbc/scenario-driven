package com.thoughtworks.teach.bank.model;

public class Name {
    private final String name;

    public Name(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object that) {
        return that instanceof Name && ((Name) that).name.equals(this.name);
    }

    public int hashCode() {
        return name.hashCode();
    }
}
