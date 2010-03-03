package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.web.View;

import java.util.Map;
import java.util.HashMap;

public class CustomerListView implements View {

    private final Bank bank;

    public CustomerListView(Bank bank) {
        this.bank = bank;
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        templateParams.put("customers", bank.customers());
        templateParams.put("deactivatedCustomers", bank.deactivatedCustomers());
        return templateParams;
    }
}
