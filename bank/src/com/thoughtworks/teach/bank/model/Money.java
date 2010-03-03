package com.thoughtworks.teach.bank.model;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Money {
    private final long pennies;

    public Money(long pounds) {
        this.pennies = pounds * 100;
    }

    public Money(long pounds, long pennies) {
        if ((pounds > 0 && pennies < 0) || (pounds < 0 && pennies > 0)) {
            throw new IllegalArgumentException("Pounds and pennies must have same sign.");
        }
        this.pennies = pennies + pounds * 100;
    }

    public Money plus(Money otherMoney) {
        return fromPennies(pennies + otherMoney.pennies);
    }

    public Money minus(Money otherMoney) {
        return fromPennies(pennies - otherMoney.pennies);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }
        Money otherMoney = (Money) obj;
        return pennies == otherMoney.pennies;
    }

    public boolean lessThan(Money otherMoney) {
        return pennies < otherMoney.pennies;
    }

    public String toString() {
        String negative;
        long displayPennies;
        String leadingZero;

        if (pennies < 0) {
            negative = "-";
            displayPennies = pennies * -1;
        } else {
            negative = "";
            displayPennies = pennies;
        }

        if ((displayPennies % 100) < 10) {
            leadingZero = "0";
        } else {
            leadingZero = "";
        }

        return negative + "£" + displayPennies / 100 + "." + leadingZero + displayPennies % 100;
    }

    public static Money fromString(String string) {
        Pattern pattern = Pattern.compile("£?([0-9]+)(?:\\.([0-9]+))?");
        Matcher matcher = pattern.matcher(string);
        long pounds;
        long pennies;
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid Money: " + string);
        }
        try {
            pounds = Integer.parseInt(matcher.group(1));
            pennies = Integer.parseInt(matcher.group(2) == null ? "0" : matcher.group(2));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Money: " + string + " exceeds maximum Money value");
        }
        return new Money(pounds, pennies);
    }

    private Money fromPennies(long pennies) {
        return new Money(0, pennies);
    }

    public Money getPercentage(Percentage percentage) {
        long interest = roundOff(percentage.calculatePercentage(pennies));
        return new Money(interest / 100, interest % 100);
    }

    private long roundOff(double doubleValue) {
        BigDecimal bd = new BigDecimal(doubleValue);
        BigDecimal bd_round = bd.setScale(0, BigDecimal.ROUND_UP);
        return bd_round.longValue();
    }
}
