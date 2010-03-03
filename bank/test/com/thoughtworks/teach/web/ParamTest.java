package com.thoughtworks.teach.web;

import junit.framework.TestCase;

public class ParamTest extends TestCase {

    public void testShouldNotBeEqualIfNameIsDifferent() {
        assertFalse(new Param("foo", "1").equals(new Param("bar", "1")));
    }

    public void testShouldNotBeEqualIfValueIsDifferent() {
        assertFalse(new Param("foo", "1").equals(new Param("foo", "2")));
    }

    public void testShouldBeEqualIfNameAndValueAreTheSame() {
        assertEquals(new Param("foo", 1), new Param("foo", 1));
    }
}
