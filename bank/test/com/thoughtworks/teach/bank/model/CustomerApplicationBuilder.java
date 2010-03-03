package com.thoughtworks.teach.bank.model;

public class CustomerApplicationBuilder {
    private static int count = 0;
    private Name name = new Name("Boris Johnson");
    private Address address = new Address("Somewhere");
    private Postcode postcode = new Postcode("LA12 6TZ");
    private NickName nickname = new NickName("boris" + count++);
    private PhoneNumber phoneNumber = new PhoneNumber("02012345678");

    public CustomerApplicationBuilder name(Name name) {
        this.name = name;
        return this;
    }

    public CustomerApplicationBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public CustomerApplicationBuilder nickname(NickName nickName) {
        this.nickname = nickName;
        return this;
    }

    public CustomerApplicationBuilder nickname(String nickName) {
        this.nickname = new NickName(nickName);
        return this;
    }

    public CustomerApplicationBuilder postcode(Postcode postcode) {
        this.postcode = postcode;
        return this;
    }

    public CustomerApplicationBuilder postcode(String postcode) {
        this.postcode = new Postcode(postcode);
        return this;
    }

    public CustomerApplicationBuilder phoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CustomerApplicationBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
        return this;
    }

    public CustomerApplication build() {
        return new CustomerApplication(name, address, postcode, nickname, phoneNumber);
    }
}
