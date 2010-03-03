package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.web.View;

import java.util.Map;
import java.util.HashMap;

public abstract class CustomerView implements View {

    private final CustomerFinder finder;

    public CustomerView(Bank bank) {
        finder = new CustomerFinder(bank);
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        templateParams.put("customer", finder.find(requestParams, "customer"));
        return templateParams;
    }
}
