package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.bank.model.NickName;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class DeactivateCustomerController implements Controller {
    private final Bank bank;

    public DeactivateCustomerController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey().startsWith("deactivateCustomer")) {
                deactivate(entry.getValue());
            }
        }
        return new Redirect(CustomerListView.class);
    }

    private void deactivate(String nickname) {
        Maybe<Customer> customer = bank.findCustomer(new NickName(nickname));
        bank.deactivateCustomer(customer.force());
    }
}
