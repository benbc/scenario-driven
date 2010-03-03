package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.web.View;

import java.util.HashMap;
import java.util.Map;

public class MakePaymentView implements View {
    private final CustomerFinder customerFinder;
    private final AccountFinder accountFinder;

    public MakePaymentView(Bank bank) {
        customerFinder = new CustomerFinder(bank);
        accountFinder = new AccountFinder(bank);
    }

    public Map<String, Object> process(Map<String, String> requestParams) {
        HashMap<String, Object> templateParams = new HashMap<String, Object>();

        Customer customer = customerFinder.find(requestParams, "fromCustomer");
        templateParams.put("fromCustomer", customer);

        if (requestParams.containsKey("toAccount")) {
            templateParams.put("toAccount", accountFinder.find(requestParams, "toAccount"));
        }

        if (requestParams.containsKey("amount")) {
            templateParams.put("amount", requestParams.get("amount").replace("?", ""));
        }

        return templateParams;
    }
}
