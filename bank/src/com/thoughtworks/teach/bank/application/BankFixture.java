package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Nothing;
import com.thoughtworks.teach.web.Fixture;

import java.util.HashMap;
import java.util.Map;

public class BankFixture extends Fixture {
    private Map<String, AccountType> accountTypes;

    public void apply() {
        Bank bank = getComponent(Bank.class);
        setupBanksAccountTypes(bank);

        Customer ben = customerWithAccount(bank, "Ben Butler-Cole", "12 The Street, London", "NW3 5RG", "ben", "02012345678");
        Account account = bank.openAccount(new AccountApplication(ben, new Nothing<AccountName>(), false, accountTypes.get("Current"), new Money(100, 0)));
        account.deposit(new Money(500, 25));

        Customer isabella = customerWithAccount(bank, "Isabella Degen", "12 The Road, London", "SW3 5RG", "isabella", "02023456789");
        Account isabellasAccount = isabella.getAccounts().iterator().next();
        isabella.addPaymentTemplate(isabellasAccount, account, new Money(200));

        customerWithAccount(bank, "Isabella Degen", "34 The Road, London", "SW1 5RG", "degen", "02023454321");
        customerWithAccount(bank, "Ola Bini", "12 High Street, London", "NW3 5XY", "ola", "02034567890");

        Customer demoTim = customerWithOverdraftAccount(bank, "Tim Wonderlink", "120, Oxford Street, London", "NW1 6WA", "tim", "02034567895");
        bank.openAccount(new AccountApplication(demoTim, new Just<AccountName>(new AccountName("Savings Account")), false, accountTypes.get("Savings"), new Money(100, 0)));

        customerWithOverdraftAccount(bank, "Mary", "12 George Street, London", "NW1 5XY", "mary", "02034567895");

        Customer harry = customerWithZeroBalanceInAccount(bank, "Harry", "Hogwarts", "HH1 43W", "harry", "02034567899");
        Account harryAccount = harry.getAccounts().iterator().next();
        harryAccount.close();
        bank.deactivateCustomer(harry);
    }

    private void setupBanksAccountTypes(Bank bank) {
        accountTypes = new HashMap<String, AccountType>();
        accountTypes.put("Current", new AccountType(new AccountTypeName("Current"), new Percentage(3.5), new Money(-5000)));
        accountTypes.put("Savings", new AccountType(new AccountTypeName("Savings"), new Percentage(6.3), new Money(-5000)));
        accountTypes.put("Student Current", new AccountType(new AccountTypeName("Student Current"), new Percentage(1.5), new Money(-1000)));
        accountTypes.put("Student Savings", new AccountType(new AccountTypeName("Student Savings"), new Percentage(8.2), new Money(-1000)));
        bank.addAccountType(accountTypes.get("Current"));
        bank.addAccountType(accountTypes.get("Savings"));
        bank.addAccountType(accountTypes.get("Student Current"));
        bank.addAccountType(accountTypes.get("Student Savings"));
    }

    private Customer customerWithAccount(Bank bank, String name, String address, String postcode, String nickName, String phoneNumber) {
        Customer customer = bank.newCustomer(new CustomerApplication(new Name(name), new Address(address),
                new Postcode(postcode), new NickName(nickName), new PhoneNumber(phoneNumber)));
        bank.openAccount(new AccountApplication(customer, new Just<AccountName>(new AccountName("Current Account")), false, accountTypes.get("Current"), new Money(100, 0)));
        return customer;
    }

    private Customer customerWithOverdraftAccount(Bank bank, String name, String address, String postcode, String nickName, String phoneNumber) {
        Customer customer = bank.newCustomer(new CustomerApplication(new Name(name), new Address(address),
                new Postcode(postcode), new NickName(nickName), new PhoneNumber(phoneNumber)));
        bank.openAccount(new AccountApplication(customer, new Just<AccountName>(new AccountName("Overdraft Account")), true, accountTypes.get("Current"), new Money(2000, 0)));
        return customer;
    }

    private Customer customerWithZeroBalanceInAccount(Bank bank, String name, String address, String postcode, String nickName, String phoneNumber) {
        Customer customer = bank.newCustomer(new CustomerApplication(new Name(name), new Address(address),
                new Postcode(postcode), new NickName(nickName), new PhoneNumber(phoneNumber)));
        Account account = bank.openAccount(new AccountApplication(customer, new Just<AccountName>(new AccountName("Current Account")), false, accountTypes.get("Current"), new Money(100, 0)));
        account.withdraw(new Money(100));
        return customer;
    }
}
