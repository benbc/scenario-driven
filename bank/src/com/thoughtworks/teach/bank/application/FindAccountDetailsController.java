package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.NickName;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class FindAccountDetailsController implements Controller {
    private final AccountFinder finder;

    public FindAccountDetailsController(Bank bank) {
        finder = new AccountFinder(bank);
    }

    public Redirect execute(Map<String, String> params) {
        Account account = finder.find(params, "account");
        NickName nickName = new NickName(params.get("nickName"));
        return new Redirect(CustomerAccountDetailsView.class, name("account").value(account), name("customer").value(nickName));
    }
}
