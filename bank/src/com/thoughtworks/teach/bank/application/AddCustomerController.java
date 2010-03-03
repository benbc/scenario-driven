package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class AddCustomerController implements Controller {
    private final Bank bank;

    public AddCustomerController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        bank.newCustomer(applicationFrom(parameters));
        return new Redirect(AdminHomepageView.class);
    }

    private CustomerApplication applicationFrom(Map<String, String> parameters) {
        Name name = nameFrom(parameters);
        Address address = addressFrom(parameters);
        Postcode postcode = postcodeFrom(parameters);
        NickName nickname = nicknameFrom(parameters);
        PhoneNumber phoneNumber = phoneNumberFrom(parameters);
        return new CustomerApplication(name, address, postcode, nickname, phoneNumber);
    }

    private PhoneNumber phoneNumberFrom(Map<String, String> parameters) {
          return new PhoneNumber(parameters.get("phoneNumber"));
    }

    private NickName nicknameFrom(Map<String, String> parameters) {
        return new NickName(parameters.get("nickName"));
    }

    private Address addressFrom(Map<String, String> parameters) {
        return new Address(parameters.get("address"));
    }

    private Postcode postcodeFrom(Map<String, String> parameters) {
         return new Postcode(parameters.get("postcode"));
    }

    private Name nameFrom(Map<String, String> parameters) {
        return new Name(parameters.get("name"));
    }
}
