package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class PaymentTemplateTest extends TestCase {

    Account fromAccount = new AccountBuilder().build();
    Account toAccount = new AccountBuilder().build();
    private Money amount = new Money(100);

    public void testThatFromAccountEqualsFromAccount() {
        PaymentTemplate paymentTemplate = new PaymentTemplate(fromAccount, toAccount, amount);
        assertEquals(fromAccount, paymentTemplate.getFromAccount());
    }

    public void testThatToAccountEqualsToAccount() {
        PaymentTemplate paymentTemplate = new PaymentTemplate(fromAccount, toAccount, amount);
        assertEquals(toAccount, paymentTemplate.getToAccount());
    }

    public void testThatAmountEqualsAmount() {
        PaymentTemplate paymentTemplate = new PaymentTemplate(fromAccount, toAccount, amount);
        assertEquals(amount, paymentTemplate.getAmount());
    }

    public void testTwoEqualPaymentTemplatesAreEqual() {
        PaymentTemplate template1 = new PaymentTemplate(fromAccount, toAccount, amount);
        PaymentTemplate template2 = new PaymentTemplate(fromAccount, toAccount, amount);
        assertTrue(template1.equals(template2));
    }

    public void testPaymentTemplateHasName() {
        PaymentTemplate paymentTemplate = new PaymentTemplate(fromAccount, toAccount, amount);
        assertEquals(toAccount.getOwner().getName() + " " + amount.toString(), paymentTemplate.getName());
    }
}
