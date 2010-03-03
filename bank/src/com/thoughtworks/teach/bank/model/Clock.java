package com.thoughtworks.teach.bank.model;

import org.joda.time.DateTime;

public interface Clock {
    DateTime getDateTime();
}
