package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import junit.framework.TestCase;
import org.picocontainer.DefaultPicoContainer;

public class BankRegistryTest extends TestCase {
    private final DefaultPicoContainer pico;

    public BankRegistryTest() {
        BankRegistry registry = new BankRegistry();
        pico = new DefaultPicoContainer();
        registry.setPico(pico);
        registry.register();
    }

    public void testShouldWireUpBankAndCustomerRepository() {
        Bank bank = pico.getComponent(Bank.class);
        CustomerRepository repository = pico.getComponent(CustomerRepository.class);
        Name bob = new Name("bob");
        bank.newCustomer(new CustomerApplicationBuilder().name(bob).build());
        assertTrue(repository.findByName(bob).size() >= 1);
    }
}
