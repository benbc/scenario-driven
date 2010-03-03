package com.thoughtworks.teach.bank.application;

import junit.framework.TestCase;
import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import static com.thoughtworks.teach.web.ParamBuilder.name;

import java.util.Map;
import java.util.HashMap;

public class FindCustomerAccountByNameControllerTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final FindCustomerAccountByNameController findCustomerAccountController = new FindCustomerAccountByNameController();
    private final Map<String, String> params = new HashMap<String, String>();
    private final Customer customer = new CustomerBuilder().build();

    public void testExecuteShouldRedirectToFoundCustomerAccountViewForFoundCustomer() {
        Redirect expected = new Redirect(FoundCustomerAccountView.class, name("customer").value(customer.getName().toString()));
        params.put("customer", customer.getName().toString());
        Redirect found = findCustomerAccountController.execute(params);
        assertEquals(expected, found);
    }
}
