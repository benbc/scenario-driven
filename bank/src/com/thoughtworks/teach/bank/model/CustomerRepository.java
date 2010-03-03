package com.thoughtworks.teach.bank.model;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;

import java.util.*;

public class CustomerRepository {
    private Map<NickName, Customer> customers = new HashMap<NickName, Customer>();

    public void add(Customer customer) {
        customers.put(customer.getNickName(), customer);
    }

    public void remove(Customer customer) {
         customers.remove(customer.getNickName());
    }

    public Set<Customer> all() {
        return Collections.unmodifiableSet(new HashSet<Customer>(customers.values()));
    }

    public List<Customer> findByName(Name name) {
        List<Customer> customersFound = new ArrayList<Customer>();
        for (Customer customer : customers.values()) {
            if (customer.getName().equals(name)) {
                customersFound.add(customer);
            }
        }
        return customersFound;
    }

    public Maybe<Customer> findByAccountNumber(AccountNumber number) {
        for (Customer customer : customers.values()) {
            for (Account account : customer.getAccounts()) {
                if (account.number().equals(number)) {
                    return new Just<Customer>(customer);
                }
            }
        }
        return new Nothing<Customer>();
    }

    public Maybe<Customer> findByNickName(NickName nickName) {
        for (Customer customer : customers.values()) {
            if (customer.getNickName().equals(nickName)) {
                return new Just<Customer>(customer);
            }
        }
        return new Nothing<Customer>();
    }

    public boolean contains(Customer customer) {
        return customers.containsValue(customer);
    }
}
