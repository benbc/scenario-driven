package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.NickName;

import java.util.Map;

public class ReactivateCustomerController implements Controller {
    private final Bank bank;

    public ReactivateCustomerController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().startsWith("reactivateCustomer")) {
                bank.reactivateCustomer(new NickName(entry.getValue()));
            }
        }
        return new Redirect(CustomerListView.class);
    }     
}
