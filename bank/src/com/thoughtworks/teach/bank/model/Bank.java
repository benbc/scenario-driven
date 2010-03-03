package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bank {
    private final CustomerRepository customers;
    private Policy onlinePaymentLimitPolicy = new OnlinePaymentLimitPolicy(new Money(1000));
    private final Set<AccountType> accountTypes = new HashSet<AccountType>();
    private final PendingPayments pendingPayments = new PendingPayments();
    private final CustomerRepository deactivatedCustomers;
    private final Clock clock;

    public Bank(CustomerRepository customers, CustomerRepository deactivatedCustomers, Clock clock) {
        this.customers = customers;
        this.deactivatedCustomers = deactivatedCustomers;
        this.clock = clock;
    }

    public Customer newCustomer(CustomerApplication application) {
        checkExistingNickname(application.nickname());
        Customer newCustomer = new Customer(application.name(), application.address(), application.postcode(),
                application.nickname(), application.phoneNumber());
        customers.add(newCustomer);
        return newCustomer;
    }

    public Set<Customer> customers() {
        return customers.all();
    }

    public Maybe<Customer> findCustomer(NickName nickName) {
        Maybe<Customer> customer = customers.findByNickName(nickName);
        if (!customer.hasValue()) {
            customer = deactivatedCustomers.findByNickName(nickName);
        }
        return customer;
    }

    public List<Customer> findByName(Name name) {
        return customers.findByName(name);
    }

    public Account openAccount(AccountApplication application) {
        validateApplication(application);
        Account account = new Account(application.customer(), application.name(), application.allowUnarrangedOverdraft(),
                application.accountType(), policyFor(application), clock);
        account.deposit(application.initialDeposit());
        return account;
    }

    public Maybe<Account> findAccount(AccountNumber number) {
        Maybe<Customer> customer = customers.findByAccountNumber(number);
        if (!customer.hasValue()) {
            return new Nothing<Account>();
        }
        for (Account account : customer.force().getAccounts()) {
            if (account.number().equals(number)) {
                return new Just<Account>(account);
            }
        }
        throw new RuntimeException("The CustomerRepository lied. Couldn't find a/c " + number + " for " + customer);
    }

    public void addAccountType(AccountType accountType) {
        accountTypes.add(accountType);
    }

    public Set<AccountType> getAccountTypes() {
        return accountTypes;
    }

    public Maybe<AccountType> findAccountType(AccountTypeName name) {
        for (AccountType accountType : accountTypes) {
            if (accountType.getAccountTypeName().equals(name)) {
                return new Just<AccountType>(accountType);
            }
        }
        return new Nothing<AccountType>();
    }

    public void addPendingPayment(Payment payment) {
        pendingPayments.addPayment(payment);
    }

    public void doDailyProcessing(LocalDate currentDate) {
        pendingPayments.settlePayments(currentDate);
        payInterestToAccounts();
    }

    public void changeInterestRate(AccountType accountType, Percentage interestRate) {
        accountType.setInterestRate(interestRate);
    }

    public void setOnlinePaymentLimit(Money limit) {
        this.onlinePaymentLimitPolicy = new OnlinePaymentLimitPolicy(limit);
    }

    public Set<Customer> deactivatedCustomers() {
        return deactivatedCustomers.all();
    }

    public void deactivateCustomer(Customer customer) {
        if (customer.getAccounts().isEmpty()) {
            customers.remove(customer);
            deactivatedCustomers.add(customer);
        } else {
            throw new IllegalStateException("Customer " + customer + " has active accounts so cannnot be deactivated");
        }
    }

    public void reactivateCustomer(NickName nickName) {
        Maybe<Customer> maybe = deactivatedCustomers.findByNickName(nickName);
        if (maybe.hasValue()) {
            Customer customer = maybe.force();
            deactivatedCustomers.remove(customer);
            customers.add(customer);
        } else {
            throw new RuntimeException("Attempt to reactivate customer not on deactivated customers list");
        }
    }

    private void payInterestToAccounts() {
        for (Customer customer : customers.all()) {
            for (Account account : customer.getAccounts()) {
                account.deposit(account.calculateInterest());
            }
        }
    }

    private void checkExistingNickname(NickName nickname) {
        Maybe<Customer> existingCustomer = customers.findByNickName(nickname);
        if (existingCustomer.hasValue()) {
            throw new IllegalArgumentException("Customer with NickName " + nickname + " already exists");
        }
    }

    private void validateApplication(AccountApplication application) {
        if (application.initialDeposit().lessThan(new Money(100))) {
            throw new IllegalArgumentException("Deposit must be at least 100 pounds to open an account");
        }
        if (application.initialDeposit().lessThan(application.accountType().getMinimumBalance())) {
            throw new IllegalArgumentException("Deposit must be at least minimum balance to open an account");
        }
        if (!customers.contains(application.customer())) {
            throw new IllegalArgumentException("Cannot open an account for a customer from a different bank");
        }
    }

    private CompositePolicy policyFor(AccountApplication application) {
        List<Policy> policies = new ArrayList<Policy>();
        policies.add(onlinePaymentLimitPolicy);
        policies.add(new MinimumBalancePolicy(application.accountType().getMinimumBalance()));
        if (!application.allowUnarrangedOverdraft()) {
            policies.add(new DenyOverdraftPolicy());
        }
        return new CompositePolicy(policies);
    }
}
