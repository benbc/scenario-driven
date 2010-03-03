package com.thoughtworks.teach.bank.application;

import junit.framework.TestCase;
import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import static com.thoughtworks.teach.web.ParamBuilder.name;

import java.util.Map;
import java.util.HashMap;

public class LoginControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Customer customer = new CustomerBuilder().build();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Map<String, String> params = new HashMap<String, String>();
    private final LoginController controller = new LoginController(bank);

    public void testThatNickNameExists() {

        customers.add(customer);

        params.put("customer", customer.getNickName().toString());

        Redirect expected = new Redirect(CustomerHomepageView.class, name("customer").value(customer.getNickName()));
        Redirect actual = controller.execute(params);
        assertEquals(expected, actual);
    }
}
