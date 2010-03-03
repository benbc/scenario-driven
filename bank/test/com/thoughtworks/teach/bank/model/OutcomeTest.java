package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class OutcomeTest extends TestCase {
    public void testOutcomeEmptyErrorMessage() {
        Outcome outcome = new Outcome(Status.Failed, "");
        assertEquals("", outcome.errorMessage());
    }

  public void testOutcomeErrorMessage() {
        Outcome outcome = new Outcome(Status.Failed, "This policy failed");
        assertEquals("This policy failed", outcome.errorMessage());
    }
}
