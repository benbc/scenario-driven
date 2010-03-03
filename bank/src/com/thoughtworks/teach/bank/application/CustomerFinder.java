package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.bank.model.NickName;
import com.thoughtworks.teach.bank.util.Maybe;

import java.util.Map;

public class CustomerFinder {
    private final Bank bank;

    public CustomerFinder(Bank bank) {
        this.bank = bank;
    }

    public Customer find(Map<String, String> params, String key) {
        checkParamExists(params, key);
        Maybe<Customer> customer = customerFrom(params, key);
        checkCustomerExists(params, key, customer);
        return customer.force();
    }

    private void checkCustomerExists(Map<String, String> params, String key, Maybe<Customer> customer) {
        if (!customer.hasValue()) {
            throw new RuntimeException("Could not find a customer with nickname '" + nicknameFrom(params, key) + "'.");
        }
    }

    private Maybe<Customer> customerFrom(Map<String, String> params, String key) {
        return bank.findCustomer(nicknameFrom(params, key));
    }

    private NickName nicknameFrom(Map<String, String> params, String key) {
        return new NickName(params.get(key));
    }

    private void checkParamExists(Map<String, String> params, String key) {
        if (!params.containsKey(key) || params.get(key).equals("")) {
            throw new RuntimeException("Could not find parameter '" + key + "' in " + params);
        }
    }
}
