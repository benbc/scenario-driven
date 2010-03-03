package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.web.Controller;
import com.thoughtworks.teach.web.Redirect;
import com.thoughtworks.teach.bank.model.Bank;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Map;

public class DailyProcessingController implements Controller {
    private final Bank bank;

    public DailyProcessingController(Bank bank) {
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        bank.doDailyProcessing(paymentDateFrom(parameters));
        return new Redirect(BankAdminView.class);
    }

    private LocalDate paymentDateFrom(Map<String, String> parameters) {
        return convertStringToDate(parameters.get("processingDate"));
    }

    private LocalDate convertStringToDate(String paymentDate) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");
        DateTime dateTime;
        try {
            dateTime = formatter.parseDateTime(paymentDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Date Entered.");
        }
        return dateTime.toLocalDate();
    }
}
