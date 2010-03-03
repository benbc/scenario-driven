package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;
import org.joda.time.LocalDate;

public class PendingPaymentTest extends TestCase {
    private Account toAc = new AccountBuilder().build();
    private Account fromAc = new AccountBuilder().deposit(new Money(200)).build();
    private Money amount = new Money(10);
    private LocalDate pastDate = new LocalDate(1950, 12, 31);
    private LocalDate futureDate = new LocalDate(2050, 12, 31);
    private LocalDate currentDate = new LocalDate(2005, 1, 1);

    public void testNewPendingPaymentsListReturnsAList() {
        PendingPayments pending = new PendingPayments();

        assertNotNull(pending.payments());
    }

    public void testNewPendingPaymentsListReturnsAnEmptyList() {
        PendingPayments pending = new PendingPayments();

        assertTrue(pending.payments().isEmpty());
    }

    public void testPendingPaymentsAddsPaymentToList() {
        PendingPayments pending = new PendingPayments();
        Payment futurePayment = new Payment(toAc, fromAc, amount, futureDate);

        pending.addPayment(futurePayment);
        assertTrue(pending.payments().contains(futurePayment));
    }

    public void testPendingPaymentSettlesACurrentDatePayment() {
        PendingPayments pending = new PendingPayments();
        Account myAc = new AccountBuilder().deposit(new Money(150)).build();
        Payment currentPayment = new Payment(myAc, fromAc, amount, currentDate);

        pending.addPayment(currentPayment);
        pending.settlePayments(currentDate);
        assertEquals(new Money(160), myAc.getBalance());
    }

    public void testPendingPaymentSettlesAPastDatePayment() {
        PendingPayments pending = new PendingPayments();
        Account myAc = new AccountBuilder().deposit(new Money(150)).build();
        Payment currentPayment = new Payment(myAc, fromAc, amount, pastDate);

        pending.addPayment(currentPayment);
        pending.settlePayments(currentDate);
        assertEquals(new Money(160), myAc.getBalance());
    }

    public void testPendingPaymentDoesNotSettleAFutureDatePayment() {
        PendingPayments pending = new PendingPayments();
        Account myAc = new AccountBuilder().deposit(new Money(150)).build();
        Payment futurePayment = new Payment(myAc, fromAc, amount, futureDate);

        pending.addPayment(futurePayment);
        pending.settlePayments(currentDate);
        assertEquals(new Money(150), myAc.getBalance());
    }

    public void testPendingPaymentDoesNotSettleAPaymentWhereTransferFailed() {
        PendingPayments pending = new PendingPayments();
        Account myAc = new AccountBuilder().build();
        Account fromAc = new AccountBuilder().policy(new AlwaysFailsPolicy()).build();
        Payment currentPayment = new Payment(myAc, fromAc, new Money(500), currentDate);

        pending.addPayment(currentPayment);
        pending.settlePayments(currentDate);
        assertEquals(new Money(0), myAc.getBalance());
    }

    public void testShouldSettleAllCurrentPendingPayments() {
        PendingPayments pending = new PendingPayments();
        Account myAc = new AccountBuilder().deposit(new Money(150)).build();
        Payment firstPayment = new Payment(myAc, fromAc, amount, currentDate);
        Payment secondPayment = new Payment(myAc, fromAc, amount, futureDate);
        Payment thirdPayment = new Payment(myAc, fromAc, new Money(15), currentDate);

        pending.addPayment(firstPayment);
        pending.addPayment(secondPayment);
        pending.addPayment(thirdPayment);
        pending.settlePayments(currentDate);
        assertEquals(new Money(175), myAc.getBalance());
    }

    public void testPendingPaymentListsOnlyFutureDatePaymentAfterSettlement() {
        PendingPayments pending = new PendingPayments();
        Customer bob = new CustomerBuilder().name(new Name("bob")).build();
        Account fromAc = new AccountBuilder().deposit(new Money(100)).customer(bob).build();
        Customer alice = new CustomerBuilder().name(new Name("alice")).build();
        Account toAc = new AccountBuilder().customer(alice).build();
        Payment firstPayment = new Payment(toAc, fromAc, amount, currentDate);
        Payment secondPayment = new Payment(toAc, fromAc, amount, futureDate);
        Payment thirdPayment = new Payment(toAc, fromAc, new Money(15), currentDate);
        pending.addPayment(firstPayment);
        pending.addPayment(secondPayment);
        pending.addPayment(thirdPayment);
        
        PendingPayments expected = new PendingPayments();
        expected.addPayment(secondPayment);

        pending.settlePayments(currentDate);
        assertEquals(expected.payments(), pending.payments());
    }
}
