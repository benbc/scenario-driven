package com.thoughtworks.teach.bank.model;

public class CustomerBuilder {
    private static int counter = 0;

    private Name name = new Name("Sarah McFiggis");
    private Address address = new Address("12 London");
    private Postcode postcode = new Postcode("EC1 7XY");
    private NickName nickName = new NickName("nickname" + counter++);
    private PhoneNumber phoneNumber = new PhoneNumber("02012345678");

    public Customer build() {
        return new Customer(name, address, postcode, nickName, phoneNumber);
    }

    public CustomerBuilder name(Name name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder address(Address address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder postcode(Postcode postcode) {
        this.postcode = postcode;
        return this;
    }

    public CustomerBuilder nickName(NickName nickName) {
        this.nickName = nickName;
        return this;
    }

    public CustomerBuilder phoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
