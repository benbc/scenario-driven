package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class CustomerRepositoryTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();

    public void testShouldBeEmptyWhenCreated() {
        assertEquals(0, repository.all().size());
    }

    public void testAddedCustomerShouldBeInReturnedSet() {
        Customer customer = new CustomerBuilder().build();
        repository.add(customer);

        assertTrue(repository.all().contains(customer));
    }

    public void testSetReturnedShouldBeUnmodifiable() {
        try {
            repository.all().add(new CustomerBuilder().build());
            fail();
        } catch (UnsupportedOperationException e) {
            // expected
        }
    }

    public void testShouldBeAbleToRetrieveCustomerByName() {
        Name name = new Name("bob");
        Customer customer = new CustomerBuilder().name(name).build();
        repository.add(customer);

        assertTrue(repository.findByName(name).size() >= 1);
    }

    public void testShouldBeAbleToRetrieveCustomerByNickName() {
        NickName nickName = new NickName("bt1");
        Customer customer = new CustomerBuilder().nickName(nickName).build();

        repository.add(customer);
        assertEquals(customer, repository.findByNickName(nickName).force());
    }

    public void testShouldBeAbleToRetrieveCustomerByAccountNumber() {
        Customer customer = new CustomerBuilder().build();
        Account account = new AccountBuilder().customer(customer).build();
        repository.add(customer);

        assertEquals(customer, repository.findByAccountNumber(account.number()).force());
    }

    public void testShouldReturnNothingIfCustomerCannotBeFoundByAccountNumber() {
        assertFalse(repository.findByAccountNumber(new AccountNumber()).hasValue());
    }

    public void testShouldContainACustomerThatHasBeenAdded() {
        Customer customer = new CustomerBuilder().build();
        repository.add(customer);
        assertTrue(repository.contains(customer));
    }

    public void testShouldNotContainACustomerThatHasNotBeenAdded() {
        Customer customer = new CustomerBuilder().build();
        assertFalse(repository.contains(customer));
    }

    public void testShouldBeAbleToRemoveACustomer() {
        Customer customer = new CustomerBuilder().build();
        repository.add(customer);
        repository.remove(customer);
        assertFalse(repository.contains(customer));
    }
}
