package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;
import org.joda.time.LocalDate;

import java.util.HashSet;
import java.util.Set;

public class BankTest extends TestCase {
    private final CustomerRepository repository = new CustomerRepository();
    private final CustomerRepository deactivatedRepository = new CustomerRepository();
    private final Bank bank = new Bank(repository, deactivatedRepository, new SystemClock());

    public void testShouldPutNewCustomerIntoRepository() {
        Customer mcFiggis = bank.newCustomer(new CustomerApplicationBuilder().build());
        assertTrue(repository.findByName(mcFiggis.getName()).size() >= 1);
        assertTrue(repository.findByNickName(mcFiggis.getNickName()).hasValue());
    }

    public void testShouldBeAbleToOpenAnAccountForACustomer() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
        assertEquals(customer, account.getOwner());
    }

    public void testShouldBeAbleToOpenAnAccountWithUnarrangedOverdraft() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).allowingOverdraft().build());
        Outcome outcome = account.withdraw(new Money(50));
        assertTrue(outcome.isSuccessful());
    }

    public void testShouldBeAbleToOpenAnAccountWithoutUnarrangedOverdraft() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).notAllowingOverdraft().build());
        Outcome outcome = account.withdraw(new Money(150));
        assertFalse(outcome.isSuccessful());
    }

    public void testShouldReturnSetOfAllCustomers() {
        Customer jane = bank.newCustomer(new CustomerApplicationBuilder().build());
        Customer tarzan = bank.newCustomer(new CustomerApplicationBuilder().build());

        Set<Customer> expected = new HashSet<Customer>();
        expected.add(jane);
        expected.add(tarzan);

        assertEquals(expected, bank.customers());
    }

    public void testShouldBeAbleToFindACustomerByName() {
        NickName nickName = new NickName("nickname");
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickName).build());
        assertEquals(customer, bank.findCustomer(nickName).force());
    }

    public void testShouldCreateACustomerWithTheRightAddress() {
        Address address = new Address("12 The street,London");
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().address(address).build());
        assertEquals(address, customer.getAddress());
    }

    public void testShouldReturnNothingForNonexistentCustomer() {
        assertFalse(bank.findCustomer(new NickName("notacustomer")).hasValue());
    }

    public void testShouldBeAbleToFindAnAccountByNumber() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).build());
        assertEquals(account, bank.findAccount(account.number()).force());
    }

    public void testShouldReturnNothingIfAccountCannotBeFoundByNumber() {
        assertFalse(bank.findAccount(new AccountNumber()).hasValue());
    }

    public void testShouldThrowAnExceptionIfDepositIsLessThanOneHundredPounds() {
        try {
            bank.openAccount(new AccountApplicationBuilder(bank).deposit(new Money(99)).build());
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Deposit must be at least 100 pounds to open an account", e.getMessage());
        }
    }

    public void testShouldThrowAnExceptionIfDepositIsLessThanMinimumBalance() {
        try {
            bank.openAccount(new AccountApplicationBuilder(bank)
                    .accountType(new AccountType(new AccountTypeName("Current"), new Percentage(0.0), new Money(200)))
                    .deposit(new Money(199)).build());
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Deposit must be at least minimum balance to open an account", e.getMessage());
        }
    }

    public void testShouldThrowAnExceptionIfNicknameAlreadyExists() {
        NickName nickname = new NickName("bob");
        bank.newCustomer(new CustomerApplicationBuilder().nickname(nickname).build());
        try {
            bank.newCustomer(new CustomerApplicationBuilder().nickname(nickname).build());
            fail();
        }
        catch (IllegalArgumentException e) {
            assertEquals("Customer with NickName bob already exists", e.getMessage());
        }
    }

    public void testShouldAddAccountType() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(2.2), new Money(0));
        bank.addAccountType(accountType);
        assertTrue(bank.getAccountTypes().contains(accountType));
    }

    public void testShouldFindAccountTypeByName() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(2.2), new Money(0));
        bank.addAccountType(accountType);
        assertTrue(bank.findAccountType(new AccountTypeName("Current")).hasValue());
        assertEquals(accountType, bank.findAccountType(new AccountTypeName("Current")).force());
    }

    public void testAllCurrentPaymentsShouldBeSettledByDailyProcessing() {
        LocalDate currentDate = new LocalDate(2008, 1, 1);
        Account toAc = new AccountBuilder().build();
        Account fromAc = new AccountBuilder().deposit(new Money(150)).build();
        Payment firstPayment = new Payment(toAc, fromAc, new Money(15), currentDate);

        bank.addPendingPayment(firstPayment);

        bank.doDailyProcessing(currentDate);
        assertEquals(new Money(15), toAc.getBalance());
    }

    public void testShouldChangeInterestRate() {
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(2.2), new Money(0));
        bank.addAccountType(accountType);
        bank.changeInterestRate(accountType, new Percentage(1.1));
        assertEquals(new Percentage(1.1), accountType.getInterestRate());
    }

    public void testShouldPlaceInitialDepositIntoAccount() {
        Money deposit = new Money(222);
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).deposit(deposit).build());
        assertEquals(deposit, account.getBalance());
    }

    public void testShouldNotBeAbleToOpenAccountForSomeoneElsesCustomer() {
        Customer customer = new CustomerBuilder().build();
        try {
            bank.openAccount(new AccountApplicationBuilder(bank).customer(customer).build());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot open an account for a customer from a different bank", e.getMessage());
        }
    }

    public void testShouldChangeBalanceWhenItPaysInterestToAnAccount() {
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).deposit(new Money(100)).build());
        bank.doDailyProcessing(new LocalDate(2008, 1, 18));
        assertEquals(new Money(100, 3), (account.getBalance()));
    }

    public void testAnAccountWithAMinimumBalanceShouldNotAllowMoreThenTheMinimumBalanceToBeWithdrawn() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(0.0), new Money(100));
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).accountType(accountType).customer(customer).build());
        Outcome outcome = account.withdraw(new Money(110));
        assertFalse(outcome.isSuccessful());
    }

    public void testAnAccountWithAMinimumBalanceShouldAllowMoreThenTheMinimumBalanceToBeWithdrawn() {
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().build());
        AccountType accountType = new AccountType(new AccountTypeName("Current"), new Percentage(0.0), new Money(100));
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).accountType(accountType).customer(customer).build());
        Outcome outcome = account.withdraw(new Money(10));
        assertTrue(outcome.isSuccessful());
    }

    public void testFailsIfOnlinePaymentAmountExceedsOnlinePaymentLimit() {
        Customer fromCustomer = bank.newCustomer(new CustomerApplicationBuilder().build());
        Customer toCustomer = bank.newCustomer(new CustomerApplicationBuilder().build());
        Account fromAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(fromCustomer).deposit(new Money(2000)).build());
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(toCustomer).build());
        Outcome outcome = fromAccount.transfer(new Money(1100), toAccount, true);
        assertFalse(outcome.isSuccessful());
    }

    public void testPassesIfOnlinePaymentAmountDoesNotExceedOnlinePaymentLimit() {
        Customer fromCustomer = bank.newCustomer(new CustomerApplicationBuilder().build());
        Customer toCustomer = bank.newCustomer(new CustomerApplicationBuilder().build());
        Account account = bank.openAccount(new AccountApplicationBuilder(bank).customer(fromCustomer).deposit(new Money(2000)).build());
        Account toAccount = bank.openAccount(new AccountApplicationBuilder(bank).customer(toCustomer).build());
        Outcome outcome = account.transfer(new Money(900), toAccount, true);
        assertTrue(outcome.isSuccessful());
    }

    public void testShouldBeAbleToDeactivateCustomer() {
        Customer mcFiggis = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(mcFiggis);
        assertFalse(repository.findByNickName(mcFiggis.getNickName()).hasValue());
        assertTrue(deactivatedRepository.findByNickName(mcFiggis.getNickName()).hasValue());
    }

    public void testShouldMoveCustomerWithoutAccountFromCustomerRepositoryToDeactivatedList() {
        Customer mcFiggis = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(mcFiggis);
        assertFalse(repository.findByName(mcFiggis.getName()).size() >= 1);
        assertTrue(deactivatedRepository.findByNickName(mcFiggis.getNickName()).hasValue());
        assertTrue(deactivatedRepository.findByName(mcFiggis.getName()).size() >= 1);
    }

    public void testthatAttemptToDeactivateCustomerWithAccountShouldThrowException() {
        Customer mcFiggis = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.openAccount(new AccountApplicationBuilder(bank).customer(mcFiggis).build());
        try {
            bank.deactivateCustomer(mcFiggis);
            fail();
        }
        catch (IllegalStateException e) {
            // expected
        }
    }

    public void testShouldReturnSetOfAllDeactivatedCustomers() {
        Customer jane = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(jane);
        Set<Customer> expected = new HashSet<Customer>();
        expected.add(jane);
        assertEquals(expected, bank.deactivatedCustomers());
    }

    public void testShouldBeAbleToFindACustomerByNameFromDeactivatedRepository() {
        NickName nickName = new NickName("nickname");
        Customer customer = bank.newCustomer(new CustomerApplicationBuilder().nickname(nickName).build());
        bank.deactivateCustomer(customer);
        assertEquals(customer, bank.findCustomer(nickName).force());
    }

    public void testShouldBeAbletoReactivateCustomer() {
        Customer mcFiggis = bank.newCustomer(new CustomerApplicationBuilder().build());
        bank.deactivateCustomer(mcFiggis);
        bank.reactivateCustomer(mcFiggis.getNickName());
        assertTrue(repository.findByNickName(mcFiggis.getNickName()).hasValue());
        assertFalse(deactivatedRepository.findByNickName(mcFiggis.getNickName()).hasValue());
    }
}
