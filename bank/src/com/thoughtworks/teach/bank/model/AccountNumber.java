package com.thoughtworks.teach.bank.model;

public class AccountNumber {
    private static int nextNumber = 1000000000;
    private final int number;

    public AccountNumber() {
        number = nextNumber();
    }

    private AccountNumber(int number) {
        this.number = number;
    }

    public String toString() {
        return Integer.toString(number);
    }

    public static AccountNumber fromString(String number) {
        if (number.equals("")) {
            throw new IllegalArgumentException("Account Number cannot be empty.");
        }
        return new AccountNumber(Integer.parseInt(number));
    }

    public boolean equals(Object that) {
        return that instanceof AccountNumber && number == ((AccountNumber) that).number;
    }

    public int hashCode() {
        return number;
    }

    private static int nextNumber() {
        int number = nextNumber;
        nextNumber = nextNumber + 1;
        return number;
    }
}
