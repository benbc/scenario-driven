package com.thoughtworks.teach.bank.model;

public class Percentage {
    private final Double value;

    public Percentage(Double value) {
        this.value = value;
    }

    public Percentage(int value) {
        this((double) value);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Percentage)) {
            return false;
        }
        Percentage otherPercentage = (Percentage) obj;
        return this.value.equals(otherPercentage.value);
    }

    public int hashCode() {
        return value.hashCode();
    }

    public String toString() {
        return value + "%";
    }

    public static Percentage fromString(String string) {
        return new Percentage(Double.parseDouble(string));
    }

    public Double calculatePercentage(long number) {
        return number * value / 100;
    }

    public double toDouble() {
        return value;
    }
}
