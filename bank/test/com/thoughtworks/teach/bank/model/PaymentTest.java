package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;
import org.joda.time.LocalDate;

public class PaymentTest extends TestCase {
    private Account toAc = new AccountBuilder().build();
    private Account fromAc = new AccountBuilder().deposit(new Money(200)).build();
    private Money amount = new Money(10);
    private LocalDate pastDate = new LocalDate(1950, 12, 31);
    private LocalDate futureDate = new LocalDate(2050, 12, 31);
    private LocalDate currentDate = new LocalDate(2005, 1, 1);

    Payment futurePayment = new Payment(toAc, fromAc, amount, futureDate);
    Payment currentPayment = new Payment(toAc, fromAc, amount, currentDate);

    public void testToStringShouldPrintPaymentDetails() {
        assertEquals((toAc.toString() + " " + fromAc.toString() + " " +
                amount.toString() + " " + futureDate.toString()), futurePayment.toString());
    }

    public void testSettlesAPaymentForMatchingDate() {
        assertTrue(currentPayment.settlePayment(currentDate));
    }

    public void testDoesNotSettleAPaymentForFutureDate() {
        assertFalse(futurePayment.settlePayment(currentDate));
    }

    public void testShouldReportFailedPaymentsAsSettled() {
        Payment unpayablePayment = new Payment(toAc, fromAc, new Money(999999999), currentDate);
        assertTrue(unpayablePayment.settlePayment(currentDate));
    }

    public void testDoesSettleAPaymentForPastDate() {
        Payment pastPayment = new Payment(toAc, fromAc, amount, pastDate);
        assertTrue(pastPayment.settlePayment(currentDate));
    }
}
