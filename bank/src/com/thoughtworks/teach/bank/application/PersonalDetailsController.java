package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;

import java.util.Map;

public class PersonalDetailsController implements Controller {
    private final Bank bank;

    public PersonalDetailsController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        Customer customer = customerFrom(parameters);
        NickName nickName = nickNameFrom(parameters);
        Address address = addressFrom(parameters);
        PhoneNumber phoneNumber = phoneNumberFrom(parameters);
        Postcode postcode = postcodeFrom(parameters);
        updateAddress(customer, address);
        updatePostcode(customer, postcode);
        updatePhoneNumber(customer, phoneNumber);
        return new Redirect(PersonalDetailsView.class, name("customer").value(nickName));
    }

    private PhoneNumber phoneNumberFrom(Map<String, String> parameters) {
        return new PhoneNumber(parameters.get("phoneNumber"));
    }

    private NickName nickNameFrom(Map<String, String> parameters) {
        return new NickName(parameters.get("customer"));
    }

    private Address addressFrom(Map<String, String> parameters) {
        return new Address(parameters.get("address"));
    }

    private Postcode postcodeFrom(Map<String, String> parameters) {
        return new Postcode(parameters.get("postcode"));
    }

    private void updateAddress(Customer customer, Address address) {
        customer.updateAddress(address);
    }

    private void updatePostcode(Customer customer, Postcode postcode) {
        customer.updatePostcode(postcode);
     }

    private void updatePhoneNumber(Customer customer, PhoneNumber phoneNumber) {
        customer.updatePhoneNumber(phoneNumber);
    }

    private Customer customerFrom(Map<String, String> requestParams) {
        NickName nickName = nickNameFrom(requestParams);
        Maybe<Customer> customer = bank.findCustomer(nickName);
        if (!customer.hasValue()) {
            throw new IllegalArgumentException("Cannot find customer called " + nickName);
        }
        return customer.force();
    }
}



