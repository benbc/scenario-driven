package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.web.View;

import java.util.HashMap;
import java.util.Map;


public class AccountServicesView implements View {
    private final AccountFinder finder;

    public AccountServicesView(Bank bank) {
        finder = new AccountFinder(bank);
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        Account account = finder.find(requestParams, "account");
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        templateParams.put("account", account);
        return templateParams;
    }
}
