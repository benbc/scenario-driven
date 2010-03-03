package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.web.View;

import java.util.HashMap;
import java.util.Map;

public class SearchNamesView implements View {

    private CustomerFinder finder;

    public SearchNamesView(Bank bank) {
        finder = new CustomerFinder(bank);
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        Customer customer = finder.find(requestParams, "fromCustomer");
        templateParams.put("fromCustomer", customer.getNickName());
        return templateParams;
    }
}
