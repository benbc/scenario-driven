package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class SearchNamesControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Map<String, String> params = new HashMap<String, String>();
    private final SearchNamesController controller = new SearchNamesController(bank);

    public void testShouldBeAbleToFindMatchingCustomerGivenTheName() {
        NickName nickname = new NickName("sybil");
        NickName nickname1 = new NickName("babitha");
        Customer customer1 = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickname1).build());
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickname).build());
        params.put("fromCustomer", customer.getNickName().toString());
        params.put("toCustomer", customer1.getNickName().toString());
        Redirect expected = new Redirect(MatchingCustomersView.class, name("fromCustomer").value(nickname), name("toCustomer").value(nickname1));
        Redirect actual = controller.execute(params);
        assertEquals(expected.toString(), actual.toString());
    }

    public void testShouldThrowExceptionIfTheNamedCustomerDoesnotExistInTheBank() {
        params.put("fromCustomer", "sophie");
        params.put("toCustomer", "james");
        try {
            controller.execute(params);
            fail();
        }
        catch (RuntimeException e) {
            // expected
        }
    }
}
