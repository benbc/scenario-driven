package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.View;

import java.util.HashMap;
import java.util.Map;

public class CustomerAccountDetailsView implements View {
    private final CustomerFinder finder;
    private final AccountFinder accountFinder;

    public CustomerAccountDetailsView(Bank bank) {
        finder = new CustomerFinder(bank);
        accountFinder = new AccountFinder(bank);
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        Account account = accountFrom(requestParams);
        templateParams.put("customer", customerFrom(requestParams));
        templateParams.put("account", account);
        templateParams.put("statement", account.getStatement());
        return templateParams;
    }

    private Account accountFrom(Map<String, String> requestParams) {
        return accountFinder.find(requestParams, "account");
    }

    private Customer customerFrom(Map<String, String> requestParams) {
        return finder.find(requestParams, "customer");
    }
}
