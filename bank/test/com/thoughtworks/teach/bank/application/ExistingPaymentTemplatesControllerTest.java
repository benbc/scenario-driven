package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class ExistingPaymentTemplatesControllerTest extends TestCase {
    private final Bank bank = new Bank(new CustomerRepository(), new CustomerRepository(), new SystemClock());
    private final Controller controller = new ExistingPaymentTemplatesController(bank);
    private final Map<String, String> params = new HashMap<String, String>();
    private final Customer fromCustomer = bank.newCustomer(new CustomerApplicationBuilder().build());

    public void testShouldRedirectToMakePaymentView() {
        Account toAccount = new AccountBuilder().build();
        params.put("fromCustomer", fromCustomer.getNickName().toString());
        Money amount = new Money(100);
        fromCustomer.addPaymentTemplate(null, toAccount, amount);
        params.put("templateId", fromCustomer.getPaymentTemplates().iterator().next().getId().toString());

        Redirect expected = new Redirect(MakePaymentView.class,
                name("fromCustomer").value(fromCustomer.getNickName()),
                name("toAccount").value(toAccount),name("amount").value(amount));

        Redirect actual = controller.execute(params);
        assertEquals(expected, actual);
    }

    public void testShouldThrowExceptionIfTemplateNameParameterIsNotFound() {
        params.put("fromCustomer", fromCustomer.getNickName().toString());
        try {
            controller.execute(params);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("No parameter templateId found in " + params, e.getMessage());
        }
    }
}
