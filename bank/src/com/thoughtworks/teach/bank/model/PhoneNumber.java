package com.thoughtworks.teach.bank.model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PhoneNumber {
    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        String number = phoneNumber.replace(" ", "");
        if (!validString(number)) {
            throw new IllegalArgumentException("Invalid phone number: " + phoneNumber);
        }
        this.phoneNumber = number;
    }

    public String toString() {
        String firstPart;
        String secondPart;
        String areaCode = phoneNumber.substring(0, 3);
        if ((areaCode.equals("020") || areaCode.equals("010"))) {
            firstPart = phoneNumber.substring(3, 7);
            secondPart = phoneNumber.substring(7);
            return areaCode + " " + firstPart + " " + secondPart;
        }
        areaCode = phoneNumber.substring(0, 5);
        firstPart = phoneNumber.substring(5);
        return areaCode + " " + firstPart;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PhoneNumber)) {
            return false;
        }
        PhoneNumber otherPhoneNumber = (PhoneNumber) obj;
        return this.phoneNumber.equals(otherPhoneNumber.phoneNumber);
    }

    public static boolean validString(String string) {
        Pattern pattern = Pattern.compile("0[0-9 ]+");
        Matcher matcher = pattern.matcher(string);
        int length = string.length();
        return matcher.matches() && length>9 && length<12 ;
    }
}
