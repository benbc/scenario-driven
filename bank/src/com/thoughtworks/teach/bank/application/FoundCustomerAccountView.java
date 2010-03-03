package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoundCustomerAccountView implements View {
    private final Bank bank;

    public FoundCustomerAccountView(Bank bank) {
        this.bank = bank;
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        List<Customer> customers = bank.findByName(new Name(requestParams.get("customer")));
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        templateParams.put("customers", customers);
        return templateParams;
    }
}
