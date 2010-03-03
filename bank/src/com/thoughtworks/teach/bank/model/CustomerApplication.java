package com.thoughtworks.teach.bank.model;

public class CustomerApplication {
    private final Name name;
    private final Address address;
    private final NickName nickname;
    private final PhoneNumber phoneNumber;
    private final Postcode postcode;

    public CustomerApplication(Name name, Address address, Postcode postcode, NickName nickname, PhoneNumber phoneNumber) {
        if (name == null || address == null || postcode == null || nickname == null || phoneNumber == null) {
            throw new IllegalArgumentException("Name, address, postcode, nickname and phone number must not be null");
        }
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public Name name() {
        return name;
    }

    public Address address() {
        return address;
    }

    public Postcode postcode() {
        return postcode;
    }

    public NickName nickname() {
        return nickname;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }
}
