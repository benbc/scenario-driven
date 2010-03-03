package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.NickName;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class SearchNamesController implements Controller {
    private final CustomerFinder finder;

    public SearchNamesController(Bank bank) {
        finder = new CustomerFinder(bank);
    }

    public Redirect execute(Map<String, String> parameters) {
        finder.find(parameters, "toCustomer");
        NickName nickName = getNickName(parameters, "fromCustomer");
        NickName toNickName = getNickName(parameters, "toCustomer");
        return new Redirect(MatchingCustomersView.class, name("fromCustomer").value(nickName), name("toCustomer").value(toNickName));
    }

    private NickName getNickName(Map<String, String> params, String key) {
        return new NickName(params.get(key));
    }
}

