package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class MakeDepositControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Map<String, String> params = new HashMap<String, String>();
    private final MakeDepositController controller = new MakeDepositController(bank);
    private final Account account;

    public MakeDepositControllerTest() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        account = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).deposit(new Money(100)).build());
    }

    public void testShouldRedirectToAccountServicesOnSuccessfulDeposit() {
        params.put("account", account.number().toString());
        params.put("amount", "£120.00");
        Redirect expected = new Redirect(AccountServicesView.class, name("account").value(account.number()));
        Redirect actual = controller.execute(params);
        assertEquals(expected, actual);
    }

    public void testShouldBeAbleToDepositMoney() {
        params.put("account", account.number().toString());
        params.put("amount", "£230.00");
        controller.execute(params);
        assertEquals(new Money(330), account.getBalance());
    }
}
