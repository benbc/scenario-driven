package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class FindCustomerAccountByNameController implements Controller {

    public Redirect execute(Map<String, String> params) {
        return new Redirect(FoundCustomerAccountView.class, name("customer").value(params.get("customer")));
    }
}
