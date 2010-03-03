package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.bank.model.PaymentTemplate;
import com.thoughtworks.teach.bank.model.PaymentTemplateID;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class ExistingPaymentTemplatesController implements Controller {
    private final CustomerFinder finder;

    public ExistingPaymentTemplatesController(Bank bank) {
        finder = new CustomerFinder(bank);
    }

    public Redirect execute(Map<String, String> params) {
        Customer fromCustomer = finder.find(params, "fromCustomer");
        PaymentTemplate template = templateFrom(params, fromCustomer);

        return new Redirect(MakePaymentView.class,
                name("fromCustomer").value(fromCustomer.getNickName()),
                name("toAccount").value(template.getToAccount()),
                name("amount").value(template.getAmount()));
    }

    private PaymentTemplate templateFrom(Map<String, String> params, Customer fromCustomer) {
        if (!params.containsKey("templateId")) {
            throw new IllegalArgumentException("No parameter templateId found in " + params);
        }
        PaymentTemplateID id = PaymentTemplateID.fromString(params.get("templateId"));
        return fromCustomer.getUniquePaymentTemplate(id);
    }
}


