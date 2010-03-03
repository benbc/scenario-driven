package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Maybe;

import java.util.*;

public class Customer {
    private final Map<AccountNumber, Account> accounts = new HashMap<AccountNumber, Account>();
    private final Name name;
    private Address address;
    private Postcode postcode;
    private final NickName nickName;
    private final Map<PaymentTemplateID, PaymentTemplate> paymentTemplates = new HashMap<PaymentTemplateID, PaymentTemplate>();
    private PhoneNumber phoneNumber;

    public Customer(Name name, Address address, Postcode postcode, NickName nickName, PhoneNumber phoneNumber) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public void addAccount(Account account) {
        if (hasAccountWithName(account.getName())) {
            throw new IllegalArgumentException("Customer already has account with this name: "
                    + account.getName());
        }
        accounts.put(account.number(), account);
    }

    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(new HashSet<Account>(accounts.values()));
    }

    public Account account(AccountNumber number) {
        return accounts.get(number);
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Postcode getPostcode() {
        return postcode;
    }

    public String toString() {
        return nickName.toString() + " : " + name.toString();
    }

    public NickName getNickName() {
        return nickName;
    }

    private boolean hasAccountWithName(Maybe<AccountName> accountName) {
        if (!accountName.hasValue()) {
            return false;
        }

        for (Account account : accounts.values()) {
            if (account.getName().hasValue()) {
                if (accountName.force().equals(account.getName().force())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updatePostcode(Postcode postcode) {
        this.postcode = postcode;
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addPaymentTemplate(Account fromAccount, Account toAccount, Money paymentAmount) {
        PaymentTemplate paymentTemplate = new PaymentTemplate(fromAccount, toAccount, paymentAmount);
        paymentTemplates.put(paymentTemplate.getId(), paymentTemplate);
    }

    public Collection<PaymentTemplate> getPaymentTemplates() {
        return paymentTemplates.values();
    }

    public PaymentTemplate getUniquePaymentTemplate(PaymentTemplateID id) {
        if (!paymentTemplates.containsKey(id)) {
            throw new IllegalArgumentException("Could not find template " + id);
        }
        return paymentTemplates.get(id);
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void removeAccount(Account account) {
        accounts.remove(account.number());
    }
}
