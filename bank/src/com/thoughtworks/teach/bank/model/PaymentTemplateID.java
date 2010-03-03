package com.thoughtworks.teach.bank.model;

public class PaymentTemplateID {
    private static int nextID = 0;
    private final Integer id;

    public PaymentTemplateID() {
         id = nextID++;
    }

    private PaymentTemplateID(int id) {
        this.id = id;
    }

    public String toString() {
        return id.toString();
    }

    public boolean equals(Object o) {
        if (o.getClass() != PaymentTemplateID.class) return false;
        PaymentTemplateID that = (PaymentTemplateID) o;
        return id.equals(that.id);
    }

    public int hashCode() {
        return id.hashCode();
    }

    public static PaymentTemplateID fromString(String string) {
        return new PaymentTemplateID(Integer.parseInt(string));
    }
}
