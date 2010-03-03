package com.thoughtworks.teach.bank.application;

import junit.framework.TestCase;
import org.picocontainer.DefaultPicoContainer;

public class BankFixtureTest extends TestCase {

    public void testShouldBeAbleToRunFixtureWithoutExceptions() {
        DefaultPicoContainer pico = new DefaultPicoContainer();

        BankRegistry registry = new BankRegistry();
        registry.setPico(pico);
        registry.register();

        BankFixture fixture = new BankFixture();
        fixture.setPico(pico);
        fixture.apply();
    }
}
