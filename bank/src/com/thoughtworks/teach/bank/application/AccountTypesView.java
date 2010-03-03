package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.web.View;
import com.thoughtworks.teach.bank.model.Bank;

import java.util.Map;
import java.util.HashMap;

public class AccountTypesView implements View {
    private final Bank bank;

    public AccountTypesView(Bank bank) {
        this.bank = bank;
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        HashMap<String, Object> templateParams = new HashMap<String, Object>();
        templateParams.put("accountTypes", bank.getAccountTypes());
        return templateParams;
    }
}