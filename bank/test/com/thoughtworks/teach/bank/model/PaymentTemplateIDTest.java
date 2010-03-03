package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class PaymentTemplateIDTest extends TestCase {

    public void testDifferentIDsShouldNotBeEqual() {
        assertFalse(new PaymentTemplateID().equals(new PaymentTemplateID()));
    }

    public void testShouldBeAbleToReconstituteAnIDFromItsToString() {
        PaymentTemplateID id = new PaymentTemplateID();
        assertEquals(id, PaymentTemplateID.fromString(id.toString()));
    }
}
