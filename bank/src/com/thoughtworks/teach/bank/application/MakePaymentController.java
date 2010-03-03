package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Controller;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Map;

public class MakePaymentController implements Controller {
    private final AccountFinder accountFinder;
    private final CustomerFinder customerFinder;
    private final Bank bank;

    public MakePaymentController(Bank bank) {
        accountFinder = new AccountFinder(bank);
        customerFinder = new CustomerFinder(bank);
        this.bank = bank;
    }

    public Redirect execute(Map<String, String> parameters) {
        Customer customer = customerFinder.find(parameters, "fromCustomer");
        Account fromAccount = accountFinder.find(parameters, "fromAccount");
        Account toAccount = accountFinder.find(parameters, "toAccount");
        Money amountRequested = amountFrom(parameters);
        String paymentDate = dateFrom(parameters);
        if (shouldCreateTemplate(parameters)) {
             customer.addPaymentTemplate(fromAccount, toAccount, amountRequested);
        }
        
        if (paymentDate.isEmpty()) {
            transfer(fromAccount, toAccount, amountRequested);
        } else {
            createPendingPayment(fromAccount, toAccount, amountRequested, paymentDate);
        }

        return new Redirect(MakePaymentView.class, name("fromCustomer").value(customer.getNickName()));
    }

    private boolean shouldCreateTemplate(Map<String, String> parameters) {
        return parameters.containsKey("createTemplate");
    }

    private void createPendingPayment(Account fromAccount, Account toAccount, Money amountRequested, String paymentDate) {
        LocalDate date = convertStringToDate(paymentDate);
        Payment payment = new Payment(toAccount, fromAccount, amountRequested, date);
        bank.addPendingPayment(payment);
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

    private String dateFrom(Map<String, String> parameters) {
        return parameters.get("paymentDate");
    }

    private void transfer(Account fromAccount, Account toAccount, Money amountRequested) {
        Outcome outcome = fromAccount.transfer(amountRequested, toAccount, true);
        if (!outcome.isSuccessful()) {
            throw new RuntimeException("Transfer of " + amountRequested + " between " + fromAccount + " and " +
                    toAccount + " failed: " + outcome.errorMessage());
        }
    }

    private Money amountFrom(Map<String, String> parameters) {
        return Money.fromString(parameters.get("amount"));
    }
}
