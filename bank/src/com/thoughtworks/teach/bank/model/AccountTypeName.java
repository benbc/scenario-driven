package com.thoughtworks.teach.bank.model;

public class AccountTypeName {
    private final String name;

    public AccountTypeName(String name) {
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccountTypeName)) {
            return false;
        }
        AccountTypeName otherAccountType = (AccountTypeName) obj;
        return name.equals(otherAccountType.name);
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return name;
    }
}
