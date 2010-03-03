package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class PercentageTest extends TestCase {
    public void testPercentagesWithSameValueShouldBeEqual() {
        assertEquals(new Percentage(2.2), new Percentage(2.2));
    }

    public void testPercentagesWithDifferentValueShouldNotBeEqual() {
        assertFalse(new Percentage(2.2).equals(new Percentage(3.2)));
    }

    public void testToStringReturnsValueWithPercentageSign() {
        assertEquals("2.2%", new Percentage(2.2).toString());
    }

    public void testIntegerPercentageShouldBeEqualToEquivalentDoublePercentage() {
        assertEquals(new Percentage(2), new Percentage(2.0));
    }

    public void testShouldParsePercentageFromString() {
        assertEquals(new Percentage(1.1), Percentage.fromString("1.1"));
    }

    public void testShouldReturnInterestOfALongValue() {
       long i= 200;
        Percentage percentage = new Percentage(10.36);
       assertEquals(20.72,percentage.calculatePercentage(i));
    }

    public void testShouldReturnDouble() {
        double doublevalue = 23.5;
        Percentage percentage = new Percentage(23.5);
        assertEquals(doublevalue, percentage.toDouble());
    }
}
