package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.AccountNumber;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.util.Maybe;

import java.util.Map;

public class AccountFinder {
    private final Bank bank;

    public AccountFinder(Bank bank) {
        this.bank = bank;
    }

    public Account find(Map<String, String> params, String key) {
        checkParamExists(params, key);
        Maybe<Account> account = accountFrom(params, key);
        checkAccountExists(params, key, account);
        return account.force();
    }

    private void checkAccountExists(Map<String, String> params, String key, Maybe<Account> account) {
        if (!account.hasValue()) {
            throw new RuntimeException("Could not find account " + numberFrom(params, key) + ".");
        }
    }

    private Maybe<Account> accountFrom(Map<String, String> params, String key) {
        return bank.findAccount(numberFrom(params, key));
    }

    private AccountNumber numberFrom(Map<String, String> params, String key) {
        return AccountNumber.fromString(params.get(key));
    }

    private void checkParamExists(Map<String, String> params, String key) {
        if (!params.containsKey(key) || params.get(key).equals("")) {
            throw new RuntimeException("Could not find parameter '" + key + "' in " + params);
        }
    }
}
