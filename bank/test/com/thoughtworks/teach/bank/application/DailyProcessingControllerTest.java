package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;
import org.joda.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

public class DailyProcessingControllerTest extends TestCase {
    private final Map<String, String> params = new HashMap<String, String>();
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Controller controller = new DailyProcessingController(bank);

    public void testBankAdminShouldRedirectToAdminPageOnSuccessfulExecute() {
        params.put("processingDate", "01-01-2008");
        Redirect actual = controller.execute(params);
        Redirect expected = new Redirect(BankAdminView.class);
        assertEquals(expected, actual);
    }

    public void testShouldThrowExceptionIfProcessingDateIsInvalid() {
        params.put("processingDate", "aa-s23*");
        try {
            controller.execute(params);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldTriggerDailyProcessingForPendingPayments() {
        params.put("processingDate", "01-01-2008");
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).rate(new Percentage(0)).build());
        Account fromAccount = bank.openAccount(new AccountApplicationBuilder(bank).deposit(new Money(200)).build());
        Money initialBalance = toAccount.getBalance();
        Money amount = new Money(30);
        bank.addPendingPayment(new Payment(toAccount, fromAccount, amount, new LocalDate(2008, 1, 1)));
        controller.execute(params);
        assertEquals(initialBalance.plus(amount), toAccount.getBalance());
    }

    public void testShouldTriggerDailyProcessingForPaymentOfinterest() {
        params.put("processingDate", "01-01-2008");
        Money initialBalance = new Money(234);
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).deposit(initialBalance).build());
        Money interest = toAccount.calculateInterest();
        controller.execute(params);
        assertEquals(initialBalance.plus(interest), toAccount.getBalance());
    }

    public void testShouldNotTriggerDailyProcessingOfPendingPaymentsBeforeSpecifiedDate() {
        params.put("processingDate", "01-01-2008");
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).rate(new Percentage(0)).build());
        Account fromAccount = bank.openAccount(new AccountApplicationBuilder(bank).deposit(new Money(200)).build());
        Money initialBalance = toAccount.getBalance();
        bank.addPendingPayment(new Payment(toAccount, fromAccount, new Money(30), new LocalDate(2008, 7, 7)));
        controller.execute(params);
        assertEquals(initialBalance, toAccount.getBalance());
    }
}

