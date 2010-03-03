package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class FindAccountDetailsControllerTest extends TestCase {
    private final Map<String, String> params = new HashMap<String, String>();
    private final CustomerRepository repository = new CustomerRepository();
    private final Bank bank = new Bank(repository, new CustomerRepository(), new SystemClock());
    private final FindAccountDetailsController findAccountDetailsController = new FindAccountDetailsController(bank);
    private final Account account;

    public FindAccountDetailsControllerTest() {
        account = bank.openAccount(new AccountApplicationBuilder(bank).build());
    }

    public void testShouldReturnDetailsOfAnAccount() {
        Customer customer = account.getOwner();
        params.put("account", account.toString());
        params.put("nickName", customer.getNickName().toString());
        Redirect expected =  new Redirect(CustomerAccountDetailsView.class, name("account").value(account), name("customer").value(customer.getNickName()));
        Redirect actual = findAccountDetailsController.execute(params);
        assertEquals(expected, actual);
    }
}
