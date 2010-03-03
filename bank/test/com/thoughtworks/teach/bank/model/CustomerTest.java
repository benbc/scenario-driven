package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

import java.util.Collection;

public class CustomerTest extends TestCase {

    public void testShouldReturnName() {
        Name name = new Name("sarah");
        Customer customer = new CustomerBuilder().name(name).build();
        assertEquals(name, customer.getName());
    }

    public void testShouldReturnAddress() {
        Address address = new Address("12 The Street,London");
        Customer customer = new CustomerBuilder().address(address).build();
        assertEquals(address, customer.getAddress());
    }

    public void testShouldReturnNickName() {
        NickName nickName = new NickName("sarah");
        Customer customer = new CustomerBuilder().nickName(nickName).build();
        assertEquals(nickName, customer.getNickName());
    }

    public void testShouldRetrieveAccountByNumber() {
        Customer customer = new CustomerBuilder().build();
        Account account = new AccountBuilder().customer(customer).build();
        assertEquals(account, customer.account(account.number()));
    }

    public void testAddingSameAccountNameShouldFail() {
        Customer customer = new CustomerBuilder().build();

        new AccountBuilder().customer(customer).name("someOtherName").build();
        try {
            new AccountBuilder().customer(customer).name("someOtherName").build();
            fail("shouldn't be able to add account with same name");
        }
        catch (IllegalArgumentException e) {
            // we expect this
        }
    }

    public void testCanAddTwoAccountsWitSameNameToDifferentCustomers() {
        Customer customer = new CustomerBuilder().name(new Name("Bob")).build();
        Customer otherCustomer = new CustomerBuilder().name(new Name("Sarah")).build();
        new AccountBuilder().customer(customer).name("Savings").build();
        new AccountBuilder().customer(otherCustomer).name("Savings").build();
        assertEquals(1, otherCustomer.getAccounts().size());
    }

    public void testCanAddTwoAccountsWithoutName() {
        Customer customer = new CustomerBuilder().build();

        new AccountBuilder().customer(customer).noName().build();
        new AccountBuilder().customer(customer).noName().build();
        assertEquals(2, customer.getAccounts().size());
    }

    public void testReturnBothNickNameAndNameFromToString() {
        Name name = new Name("Sarah Trough-Puddle");
        NickName nickName = new NickName("sarah");
        Customer customer = new CustomerBuilder().name(name).nickName(nickName).build();
        String displayName = "sarah : Sarah Trough-Puddle";
        assertEquals(displayName, customer.toString());
    }

    public void testShouldBeAbleToUpdateAnAddress() {
        Address address = new Address("12 The Street,London");
        Address newAddress = new Address("14 The Street,London");
        Customer customer = new CustomerBuilder().address(address).build();
        customer.updateAddress(newAddress);
        assertEquals(newAddress, customer.getAddress());
    }

    public void testCreatingANewPaymentTemplateAddsTemplateToCustomersTemplateList() {
        Customer customer = new CustomerBuilder().build();
        Account fromAccount = new AccountBuilder().customer(customer).build();
        Account toAccount = new AccountBuilder().build();
        Money paymentAmount = new Money(100);
        PaymentTemplate template = new PaymentTemplate(fromAccount, toAccount, paymentAmount);

        customer.addPaymentTemplate(fromAccount, toAccount, paymentAmount);

        Collection<PaymentTemplate> templates = customer.getPaymentTemplates();

        assertTrue(templates.contains(template));

    }

    public void testShouldRemoveAccount() {
        Customer customer = new CustomerBuilder().build();
        Account account = new AccountBuilder().customer(customer).build();
        customer.removeAccount(account);
        assertFalse(customer.getAccounts().contains(account));
    }

    public void testCanFindPaymentTemplateByNameOfToCustomerAndAmount() {
        Customer fromCustomer = new CustomerBuilder().name(new Name("Bob")).build();
        Customer toCustomer = new CustomerBuilder().name(new Name("Joe")).build();
        Account fromAccount = new AccountBuilder().customer(fromCustomer).build();
        Account toAccount = new AccountBuilder().customer(toCustomer).build();

        Money paymentAmount = new Money(100);
        PaymentTemplate Template = new PaymentTemplate(fromAccount, toAccount, paymentAmount);
        fromCustomer.addPaymentTemplate(fromAccount, toAccount, paymentAmount);


        fromCustomer.getPaymentTemplates();

        assertEquals(toCustomer.getName() + " " + paymentAmount.toString(), Template.getName());
    }

    public void testCanFindPaymentTemplateByIdOfPaymentTemplate() {
        Customer fromCustomer = new CustomerBuilder().name(new Name("Bob")).build();
        Customer toCustomer = new CustomerBuilder().name(new Name("Joe")).build();
        Account fromAccount = new AccountBuilder().customer(fromCustomer).build();
        Account toAccount = new AccountBuilder().customer(toCustomer).build();

        Money paymentAmount = new Money(100);
        fromCustomer.addPaymentTemplate(fromAccount, toAccount, paymentAmount);

        Collection<PaymentTemplate> templates = fromCustomer.getPaymentTemplates();
        PaymentTemplate Template = templates.iterator().next();

        PaymentTemplate template = fromCustomer.getUniquePaymentTemplate(Template.getId());

        assertEquals(template, Template);
        assertTrue(templates.contains(template));
    }

    public void testShouldThrowIllegalArgumentExceptionIfTemplateNotFound() {
        Customer customer = new CustomerBuilder().build();
        PaymentTemplateID id = new PaymentTemplateID();
        try {
            customer.getUniquePaymentTemplate(id);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Could not find template " + id, e.getMessage());
        }
    }
}
