package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class MakeTransferControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Map<String, String> params = new HashMap<String, String>();
    private final MakeTransferController controller = new MakeTransferController(bank);
    private final Account fromAccount;
    private final Account toAccount;
    private final Money amount = new Money(10);
    private final NickName nickName = new NickName("sybil");
    private final Money fromAccountInitialBalance = new Money(100);
    private final Money toAccountInitialBalance = new Money(100);

    public MakeTransferControllerTest() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickName).build());
        fromAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).deposit(fromAccountInitialBalance).build());
        toAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).deposit(toAccountInitialBalance).build());
        params.put("from", fromAccount.number().toString());
        params.put("to", toAccount.number().toString());
        params.put("amount", amount.toString());
    }

    public void testShouldRedirectToCustomerHomepage() {
        Redirect expected = new Redirect(CustomerHomepageView.class, name("customer").value(nickName));
        Redirect actual = controller.execute(params);
        assertEquals(expected, actual);
    }

    public void testShouldTransferMoneyIfFundsAreAvailable() {
        controller.execute(params);

        assertEquals(fromAccountInitialBalance.minus(amount), fromAccount.getBalance());
        assertEquals(toAccountInitialBalance.plus(amount), toAccount.getBalance());
    }

    public void testShouldNotTransferMoneyIfFundsAreNotAvailable() {
        params.put("amount", new Money(10000).toString());
        try {
            controller.execute(params);
            fail();
        } catch (RuntimeException exception) {
            assertEquals("The balance for account " + fromAccount + " must not fall below £0.00", exception.getMessage());
        }

        assertEquals(fromAccountInitialBalance, fromAccount.getBalance());
        assertEquals(toAccountInitialBalance, toAccount.getBalance());
    }
}
