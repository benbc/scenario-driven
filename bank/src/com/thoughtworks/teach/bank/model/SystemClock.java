package com.thoughtworks.teach.bank.model;

import org.joda.time.DateTime;

public class SystemClock implements Clock {
    public DateTime getDateTime() {
        return new DateTime();
    }
}
