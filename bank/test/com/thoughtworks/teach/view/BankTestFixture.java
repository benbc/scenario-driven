package com.thoughtworks.teach.view;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.web.Fixture;

public class BankTestFixture extends Fixture {
    private Bank bank;

    public void apply() {
        bank = getComponent(Bank.class);
        Customer fredTheTestGuy = addCustomer("Fred the testguy", "fred1", "12 Test Street, London", "WC1 77A", "02088889999");
        openAccountWithAccountName(fredTheTestGuy, "CurrentAccount", new AccountType(new AccountTypeName("Current"), new Percentage(1.2), new Money(0)), new Money(1299, 55));
        openAccountWithAccountName(fredTheTestGuy, "SavingAccount", new AccountType(new AccountTypeName("Savings"), new Percentage(6.89), new Money(0)), new Money(107899, 60));
    }

    public Bank bank() {
        return bank;
    }

    public Customer testCustomer() {
        return bank.findCustomer(new NickName("fred1")).force();
    }

    private Customer addCustomer(String name, String nickName, String address, String postcode, String phoneNumber) {
        CustomerApplication application = new CustomerApplicationBuilder()
                .name(new Name(name))
                .nickname(new NickName(nickName))
                .address(new Address(address))
                .postcode(new Postcode(postcode))
                .phoneNumber(new PhoneNumber(phoneNumber))
                .build();
        return bank.newCustomer(application);
    }

    private Account openAccountWithAccountName(Customer customer, String accountName, AccountType accountType, Money deposit) {
        AccountApplication application = new AccountApplication(customer,
                new Just<AccountName>(new AccountName(accountName)), true, accountType, deposit);
        return bank.openAccount(application);
    }
}
