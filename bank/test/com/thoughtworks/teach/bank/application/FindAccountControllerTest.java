package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class FindAccountControllerTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final Bank bank = new Bank(repository, new CustomerRepository(), new SystemClock());
    private final FindAccountController findAccountController = new FindAccountController(bank);
    private final Map<String, String> params = new HashMap<String, String>();
    private final Account account;
    private final AccountNumber accountNumber;

    public FindAccountControllerTest() {
        account = bank.openAccount(new AccountApplicationBuilder(bank).build());
        accountNumber = account.number();
    }

    public void testExecuteShouldRedirectToFoundAccountViewForFoundAccountNumber() {
        Redirect expected = new Redirect(FoundAccountView.class, name("account").value(account.number()));
        params.put("account", accountNumber.toString());
        Redirect found = findAccountController.execute(params);
        assertEquals(expected, found);
    }

    public void testExecuteShouldThrowExceptionForNonExistentAccountNumber() {
        params.put("account", "12345");
        try {
            findAccountController.execute(params);
            fail();
        }
        catch (RuntimeException e) {
            // expected
        }
    }
}
