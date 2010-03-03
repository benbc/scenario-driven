package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import static com.thoughtworks.teach.web.ParamBuilder.name;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;
import org.joda.time.LocalDate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MakePaymentControllerTest extends TestCase {
    private final CustomerRepository customers = new CustomerRepository();
    private final Bank bank = new Bank(customers, new CustomerRepository(), new SystemClock());
    private final Map<String, String> params = new HashMap<String, String>();
    private final MakePaymentController controller = new MakePaymentController(bank);
    private final Account fromAccount;
    private final Account toAccount;
    private final NickName nickname = new NickName("nickname1");
    private final Money amount = new Money(120);
    private final Money fromAccountInitialBalance = new Money(200);
    private final Money toAccountInitialBalance = new Money(100);

    public MakePaymentControllerTest() {
        Customer customer1 = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickname).build());
        Customer customer2 = bank.newCustomer(new CustomerApplicationBuilder().build());
        fromAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer1).deposit(fromAccountInitialBalance).build());
        toAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer2).deposit(toAccountInitialBalance).build());
        params.put("fromAccount", fromAccount.number().toString());
        params.put("toAccount", toAccount.number().toString());
        params.put("amount", amount.toString());
        params.put("fromCustomer", nickname.toString());
        params.put("paymentDate", "");
    }

    public void testShouldRedirectToMakePaymentOnSuccessfulPayment() {
        Redirect expected = new Redirect(MakePaymentView.class, name("fromCustomer").value(nickname));
        Redirect actual = controller.execute(params);
        assertEquals(expected, actual);
    }

    public void testShouldTransferMoneyIfFundsAreAvailable() {
        controller.execute(params);
        assertEquals(fromAccountInitialBalance.minus(amount), fromAccount.getBalance());
        assertEquals(toAccountInitialBalance.plus(amount), toAccount.getBalance());
    }

    public void testShouldThrowExceptionIfFundsAreNotAvailable() {
        Money excessiveAmount = new Money(120000);
        Map<String, String> params = new HashMap<String, String>();
        params.put("amount", excessiveAmount.toString());
        try {
            controller.execute(params);
            fail();
        }
        catch (RuntimeException e) {
        }
    }

    public void testShouldThrowExceptionIfFromAccountIsNonExistent() {
        Money amount = new Money(10);
        Map<String, String> params = new HashMap<String, String>();
        params.put("fromAccount", "123456");
        params.put("amount", amount.toString());
        try {
            controller.execute(params);
            fail();
        }
        catch (RuntimeException e) {
        }
    }

    public void testShouldNotTransferMoneyIfFundsAreNotAvailable() {
        params.put("amount", new Money(100000).toString());
        try {
            controller.execute(params);
            fail();
        } catch (RuntimeException exception) {
        }

        assertEquals(fromAccountInitialBalance, fromAccount.getBalance());
        assertEquals(toAccountInitialBalance, toAccount.getBalance());
    }

    public void testShouldThrowIfFundsAreNotAvailable() {
        params.put("amount", new Money(999).toString());
        try {
            controller.execute(params);
            fail();
        } catch (RuntimeException exception) {
            assertEquals("Transfer of £999.00 between " + fromAccount + " and " + toAccount + " failed: The balance for account " +
                    fromAccount + " must not fall below £0.00", exception.getMessage());
        }
    }

    public void testShouldCreatePendingPaymentIfDateIsSet() {
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).rate(new Percentage(0)).build());
        params.put("toAccount", toAccount.number().toString());
        params.put("paymentDate", "31-12-2000");
        fromAccount.deposit(new Money(100));
        Money toBalance = toAccount.getBalance();

        controller.execute(params);
        bank.doDailyProcessing(new LocalDate(2000, 12, 31));
        assertEquals(toBalance.plus(amount), toAccount.getBalance());
    }

    public void testShouldForwardToFailurePageIfInvalidValueInDate() {
        params.put("paymentDate", "40-12-2001");
        try {
            controller.execute(params);
            fail();
        } catch (RuntimeException exception) {
            assertEquals("Invalid Date Entered.", exception.getMessage());
        }
    }

    public void testShouldForwardToFailurePageIfInvalidSeparatorsInDate() {
        params.put("paymentDate", "31/12/2001");
        try {
            controller.execute(params);
            fail();
        } catch (RuntimeException exception) {
            assertEquals("Invalid Date Entered.", exception.getMessage());
        }
    }

    public void testIfPaymentTemplateIndicatorSetToTrueAddsAPaymentTemplate() {
        params.put("createTemplate", "on");

        Redirect redirect = controller.execute(params);
        assertEquals(new Redirect(MakePaymentView.class, name("fromCustomer").value(nickname)), redirect);

        Maybe<Customer> maybeCustomer = customers.findByNickName(nickname);
        assertTrue(maybeCustomer instanceof Just);

        Customer customer = maybeCustomer.force();
        Collection<PaymentTemplate> templates = customer.getPaymentTemplates();
        PaymentTemplate paymentTemplate = new PaymentTemplate(fromAccount, toAccount, amount);

        assertTrue(templates.contains(paymentTemplate));
    }

    public void testShouldRedirectToMakePaymentsPage() {
        Redirect expected = new Redirect(MakePaymentView.class, name("fromCustomer").value(nickname));
        Redirect actual = controller.execute(params);
        assertEquals(expected, actual);
    }
}
