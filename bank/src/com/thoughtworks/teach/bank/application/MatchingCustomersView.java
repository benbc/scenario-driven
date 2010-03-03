package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.web.View;

import java.util.HashMap;
import java.util.Map;

public class MatchingCustomersView implements View {
    private final CustomerFinder finder;

    public MatchingCustomersView(Bank bank) {
        finder = new CustomerFinder(bank);
    }
   
    public Map<String, Object> process(Map<String, String> requestParams) {
        Customer fromCustomer = finder.find(requestParams, "fromCustomer");
        Customer toCustomer = finder.find(requestParams, "toCustomer");
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        templateParams.put("fromCustomer", fromCustomer.getNickName());
        templateParams.put("toCustomerName", toCustomer.getName());
        templateParams.put("toAccounts", toCustomer.getAccounts());
        return templateParams;
    }
}
