package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class BankAdminControllerTest extends TestCase {
    private final Map<String, String> params = new HashMap<String, String>();
    private final CustomerRepository customers = new CustomerRepository();
    Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final BankAdminController controller = new BankAdminController(bank);
    private String onlinePaymentAmountLimit = "100";


    public void testBankAdminShouldRedirectToAdminPageOnSuccessfulExecute() {
        params.put("onlinePaymentAmountLimit", onlinePaymentAmountLimit);
        Redirect actual = controller.execute(params);
        Redirect expected = new Redirect(BankAdminView.class);
        assertEquals(expected, actual);
    }

    public void testShouldThrowAnExceptionIfOnlinePaymentLimitIsEmpty() {
        try {
            params.put("onlinePaymentAmountLimit", "");
            controller.execute(params);
            fail("Expected exception not thrown when online payment limit is empty");
        } catch (RuntimeException e) {
            assertEquals("Online Payment Limit should not be blank", e.getMessage());
        }
    }

    public void testShouldNotTransferMoreThanChangedOnlinePaymentLimit() {
        params.put("onlinePaymentAmountLimit", onlinePaymentAmountLimit);
        controller.execute(params);
        Account fromAccount = bank.openAccount(new AccountApplicationBuilder(bank).build());
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).build());
        Money fromBalance = fromAccount.getBalance();
        Money toBalance = toAccount.getBalance();
        Money amount = new Money(999);
        fromAccount.transfer(amount, toAccount, true);
        assertEquals(fromBalance, fromAccount.getBalance());
        assertEquals(toBalance, toAccount.getBalance());
    }
}
