package com.thoughtworks.teach.bank.model;

import org.joda.time.DateTime;

public class TestClock implements Clock {
    private DateTime dateTime;

    public TestClock(DateTime dateTime) {
         this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }
}
