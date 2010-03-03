package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.*;
import com.thoughtworks.teach.web.Redirect;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountControllerTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final Bank bank = new Bank(repository, new CustomerRepository(), new SystemClock());
    private final CreateAccountController createAccountController = new CreateAccountController(bank);
    private final Customer customer = new CustomerBuilder().build();
    private final Map<String, String> params = new HashMap<String, String>();

    protected void setUp() throws Exception {
        repository.add(customer);
        bank.addAccountType(new AccountType(new AccountTypeName("Current"), new Percentage(1.2), new Money(0)));
        bank.addAccountType(new AccountType(new AccountTypeName("Savings"), new Percentage(6.7), new Money(0)));
        params.put("owner", customer.getNickName().toString());
        params.put("accountType", new AccountTypeName("Current").toString());
        params.put("deposit", "100");
    }

    public void testShouldAddAccountForTheSpecifiedCustomer() {
        params.put("owner", customer.getNickName().toString());
        createAccountController.execute(params);
        assertEquals(1, customer.getAccounts().size());
    }

    public void testShouldAddAccountWithoutUnarrangedOverdraft() {
        createAccountController.execute(params);
        Account account = onlyAccountOf(customer);
        Outcome outCome = account.withdraw(new Money(500));
        assertFalse(outCome.isSuccessful());
    }

    public void testShouldAddAccountWithUnarrangedOverdraft() {
        params.put("allowUnarrangedOverdraft", "1");
        createAccountController.execute(params);
        Account account = onlyAccountOf(customer);
        Outcome outCome = account.withdraw(new Money(50));
        assertTrue(outCome.isSuccessful());
    }

    public void testShouldThrowExceptionIfCustomerDoesntExist() {
        params.put("owner", "nosuchperson");
        try {
            createAccountController.execute(params);
            fail();
        }
        catch (RuntimeException e) {
            //expected
        }
    }

    public void testShouldRedirectBackToAdminHomepage() {
        Redirect redirect = createAccountController.execute(params);
        assertEquals(new Redirect(AdminHomepageView.class), redirect);
    }

    public void testShouldAddAccountWithSpecifiedaccountType() {
        params.put("accountType", new AccountTypeName("Current").toString());
        createAccountController.execute(params);
        assertEquals(1, customer.getAccounts().size());
        Account account = onlyAccountOf(customer);
        assertEquals(bank.findAccountType(new AccountTypeName("Current")).force(), account.getAccountType());
    }

    public void testShouldAddAccountWithDepositNotLessThanAHundredPounds() {
        params.put("deposit", "100");
        createAccountController.execute(params);
        Account account = onlyAccountOf(customer);
        assertTrue(new Money(99, 99).lessThan(account.getBalance()));
    }

    public void testCreateAccountFailureShouldShouldThrowExceptionIfDepositLessThanOneHundred() {
        params.put("deposit", "99");
        try {
            createAccountController.execute(params);
            fail();
        } catch (RuntimeException e) {
        }
    }

    public void testShouldAddAccountNameToAccount() {
        params.put("accountName", "Savings");
        createAccountController.execute(params);
        Account account = onlyAccountOf(customer);
        assertEquals(new AccountName("Savings"), account.getName().force());
    }

    private Account onlyAccountOf(Customer customer) {
        return customer.getAccounts().iterator().next();
    }
}
