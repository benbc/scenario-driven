package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {

    public void testShouldBeAbleToAddTwoPoundValues() {
        Money onePound = new Money(1);
        Money twoPounds = new Money(2);
        Money threePounds = new Money(3);

        Money result = onePound.plus(twoPounds);
        assertEquals(threePounds, result);
    }

    public void testTwoEqualPoundValuesAreEqual() {
        Money firstFourPounds = new Money(4);
        Money secondFourPounds = new Money(4);
        assertTrue(firstFourPounds.equals(secondFourPounds));
    }

    public void testTwoDifferentPoundValuesAreNotEqual() {
        Money sixPounds = new Money(6);
        Money elevenPounds = new Money(11);
        assertFalse(sixPounds.equals(elevenPounds));
    }

    public void testAddingANegativeAmountGiveASmalllerResult() {
        Money sevenPounds = new Money(7);
        Money minusThreePounds = new Money(-3);
        assertEquals(new Money(4), sevenPounds.plus(minusThreePounds));
    }

    public void testAmountsWithDifferentPenniesShouldNotBeEqual() {
        Money fiveFifty = new Money(5, 50);
        Money fiveForty = new Money(5, 40);
        assertFalse(fiveFifty.equals(fiveForty));
    }

    public void testPenniesAddUp() {
        assertEquals(new Money(10, 70), new Money(5, 40).plus(new Money(5, 30)));
    }

    public void testPenniesShouldAddUpToWholePounds() {
        assertEquals(new Money(1, 35), new Money(0, 70).plus(new Money(0, 65)));
    }

    public void testAddingHugeNumbersOfPenniesShouldGiveTheRightAnswer() {
        assertEquals(new Money(500), new Money(0, 25000).plus(new Money(0, 25000)));
    }

    public void testShouldThrowAnExceptionIfPoundsAndPenniesHaveDifferentSigns() {
        try {
            new Money(1, -1);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldBeAbleToSubtractPounds() {
        Money amount = new Money(500, 50);
        Money otherAmount = new Money(200);
        assertEquals(new Money(300, 50), amount.minus(otherAmount));
    }

    public void testShouldBeAbleToSubtractNegativePennies() {
        Money biggerAmount = new Money(1, 20);
        Money smallerAmount = new Money(0, 80);
        assertEquals(new Money(0, 40), biggerAmount.minus(smallerAmount));
    }

    public void testLargerAmountShouldNotBeLessThanSmallerAmount() {
        assertFalse(new Money(100).lessThan(new Money(50)));
    }

    public void testSmallerAmountShouldBeLessThanLargerAmount() {
        assertTrue(new Money(50).lessThan(new Money(100)));
    }

    public void testSmallerPenniesShouldBeLessThanLargerPennies() {
        assertTrue(new Money(0, 80).lessThan(new Money(0, 81)));
    }

    public void testLargerPenniesShouldNotBeLessThanSmallerPennies() {
        assertFalse(new Money(0, 81).lessThan(new Money(0, 80)));
    }

    public void testAmountShouldNotBeLessThanEqualAmount() {
        assertFalse(new Money(1).lessThan(new Money(1)));
    }

    public void testSmallerNegativeAmountIsLessThanBiggerNegativeAmount() {
        assertTrue(new Money(-300).lessThan(new Money(0)));
    }

    public void testShouldPutPoundAtStartOfToString() {
        assertTrue(new Money(100).toString().startsWith("£"));
    }

    public void testToStringShouldContainPoundAmount() {
        assertTrue(new Money(100).toString().contains("100"));
    }

    public void testToStringShouldContainPennyAmount() {
        assertTrue(new Money(50, 25).toString().contains("50.25"));
    }

    public void testShouldBeAbleToDeserializePoundAmount() {
        assertEquals(new Money(22), Money.fromString(new Money(22).toString()));
    }

    public void testShouldBeAbleToDeserializePennyAmount() {
        assertEquals(new Money(22, 33), Money.fromString(new Money(22, 33).toString()));
    }

    public void testShouldThrowExceptionIfTryingToDeserializeInvalidString() {
        try {
            Money.fromString("fish");
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    public void testShouldBeAbleToCreateMoneyFromLongInputExceedingMaximumIntegerValue() {
        long maxValue = Long.MAX_VALUE/100;
        Money hugeAmount = new Money(maxValue);
        assertEquals("£" + String.valueOf(maxValue) + ".00", hugeAmount.toString());
    }

    public void testShouldBeAbleToDeserializeStringWithoutLeadingPoundSign() {
        assertEquals(new Money(100), Money.fromString("100.00"));
    }

    public void testShouldBeAbleToDeserializeStringWithoutPennies() {
        assertEquals(new Money(100), Money.fromString("£100"));
    }

    public void testToStringShouldDisplayZeroPenceWith2DecimalPlaces() {
        assertEquals("£50.00", new Money(50).toString());
    }

    public void testToStringShouldDisplayPenniesCorrectly() {
        assertEquals("£50.01", new Money(50, 1).toString());
    }

    public void testToStringShouldDisplayDoubleDigitPenniesCorrectly() {
        assertEquals("£50.21", new Money(50, 21).toString());
    }

    public void testToStringShouldDisplayNegativePenniesCorrectly() {
        assertEquals("-£50.21", new Money(-50, -21).toString());
    }

    public void testShouldReturnInterestOnMoney(){
       Percentage percentage = new Percentage(10);
        Money money = new Money(100);
       assertEquals(new Money(10),money.getPercentage(percentage));
    }

     public void testShouldReturnInterestOnMoneyWithPennies(){
       Percentage percentage = new Percentage(10.5);
        Money money = new Money(100,50);
       assertEquals(new Money(10,56),money.getPercentage(percentage));
    }
}