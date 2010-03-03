package com.thoughtworks.teach.bank.model;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PendingPayments {
    List<Payment> payments = new ArrayList<Payment>();

    public List<Payment> payments() {
        return payments;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public void settlePayments(LocalDate currentDate) {
        Iterator<Payment> it = payments.iterator();
        while (it.hasNext()) {
            Payment paymt = it.next();
            if (paymt.settlePayment(currentDate)) {
                it.remove();
            }
        }
    }
}
