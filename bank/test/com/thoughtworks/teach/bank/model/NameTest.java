package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class NameTest extends TestCase {

    public void testNamesShouldBeEqualForTheSameName() {
        assertEquals(new Name("Ben"), new Name("Ben"));
    }

    public void testNamesShouldBeUnequalForDifferentNames() {
        assertFalse(new Name("Ben").equals(new Name("Isabella")));
    }

    public void testToStringShouldReturnTheName() {
        assertEquals("Ben", new Name("Ben").toString());
    }

    public void testShouldNotAllowNullName() {
        try {
            new Name(null);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
