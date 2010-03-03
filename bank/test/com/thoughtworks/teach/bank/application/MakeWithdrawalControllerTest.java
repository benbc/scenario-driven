package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class MakeWithdrawalControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Account account;

    private final MakeWithdrawalController controller = new MakeWithdrawalController(bank);
    private final Map<String, String> params = new HashMap<String, String>();

    public MakeWithdrawalControllerTest() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        account = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).deposit(new Money(200)).build());
    }

    public void testThatMoneyIsWithdrawnFromAccount() {
        params.put("account", account.number().toString());
        params.put("amount", "£50.00");
        controller.execute(params);

        assertEquals(new Money(150), account.getBalance());

    }

    public void testThatControllerRedirectsToTheSamePage() {
        params.put("account", account.number().toString());
        params.put("amount", "£20.00");
        Redirect expected = new Redirect(AccountServicesView.class, name("account").value(account.number()));
        Redirect actual = controller.execute(params);

        assertEquals(expected.toString(), actual.toString());
    }

    public void testShouldThrowAnExceptionIfWithdrawalFails() {
        params.put("account", account.number().toString());
        params.put("amount", "£220.00");
        try {
            controller.execute(params);
            fail();
        } catch (RuntimeException exception) {
            assertEquals("You have insufficient funds in your account", exception.getMessage());
        }
    }

}
