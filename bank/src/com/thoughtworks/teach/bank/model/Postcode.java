package com.thoughtworks.teach.bank.model;

import java.util.regex.Pattern;

public class Postcode {
    private final String postcode;

    public Postcode(String postcode) {
        if (!validString(postcode)) {
            throw new IllegalArgumentException("Postcode must consist only of numbers and uppercase letters.");
        }
        this.postcode = postcode;
    }

    public String toString() {
        return postcode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Postcode)) {
            return false;
        }
        Postcode otherPostcode = (Postcode) obj;
        return postcode.equals(otherPostcode.postcode);
    }

    public static boolean validString(String postcode) {
        try {
            boolean validCharacters = Pattern.matches("[A-Z 0-9]+", postcode);
            String first = postcode.substring(0, 1);
            boolean validFirstCharacter = Pattern.matches("[A-Z]", first);
            int length = postcode.length();
            return validCharacters && validFirstCharacter && length >= 5 && length <= 8;
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Invalid postcode entered");
        }
    }
}
