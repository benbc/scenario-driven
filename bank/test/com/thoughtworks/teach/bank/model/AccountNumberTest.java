package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;

public class AccountNumberTest extends TestCase {

    public void testEachAccountNumberShouldBeUnique() {
        assertFalse(new AccountNumber().equals(new AccountNumber()));
    }

    public void testAnAccountShouldBeEqualToItself() {
        AccountNumber accountNumber = new AccountNumber();
        assertEquals(accountNumber, accountNumber);
    }

    public void testAnAccountNumberShouldHaveTenDigits() {
        String representation = new AccountNumber().toString();
        assertEquals(10, representation.length());
    }

    public void testAnAccountNumberShouldBeANumber() {
        String representation = new AccountNumber().toString();
        throwExceptionIfNotANumber(representation);
    }

    public void testAnAccountNumberIsOneGreaterThanThePreviousAccountNumber() {
        AccountNumber first = new AccountNumber();
        AccountNumber second = new AccountNumber();
        int firstNumber = Integer.parseInt(first.toString());
        int secondNumber = Integer.parseInt(second.toString());

        assertEquals(firstNumber + 1, secondNumber);
    }

    public void testAccountNumberCreatedFromStringShouldHaveCorrectRepresentation() {
        String number = "1000000021";
        assertEquals(number, AccountNumber.fromString(number).toString());
    }

    public void testTwoAccountNumbersWithTheSameNumberShouldBeEqual() {
        String number = "1000000021";
        assertEquals(AccountNumber.fromString(number), AccountNumber.fromString(number));
    }

    private void throwExceptionIfNotANumber(String string) {
        Integer.parseInt(string);
    }
}
