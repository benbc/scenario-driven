package com.thoughtworks.teach.bank.model;

import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccountTest extends TestCase {

    private Customer bob = new CustomerBuilder().build();
    private Customer alice = new CustomerBuilder().name(new Name("alice")).build();

    public void testANewAccountShouldHaveAnAccountNumber() {
        Account account = new AccountBuilder().build();
        AccountNumber number = account.number();
        assertNotNull(number);
    }

    public void testAnAccountsNumberShouldNotChange() {
        Account account = new AccountBuilder().build();
        assertEquals(account.number(), account.number());
    }

    public void testHasCorrectBalanceAfterDeposit() {
        Account account = new AccountBuilder().build();
        Money amount = new Money(1000, 88);
        account.deposit(amount);
        assertEquals(amount, account.getBalance());
    }

    public void testWithdrawalShouldReduceBalanceByWithdrawnAmount() {
        Money deposit = new Money(100);
        Account account = new AccountBuilder().deposit(deposit).build();
        Money withdrawal = new Money(30);
        account.withdraw(withdrawal);
        assertEquals(deposit.minus(withdrawal), account.getBalance());
    }

    public void testShouldReduceBalanceIfTransferringMoneyToOtherAccount() {
        Money deposit = new Money(100);
        Account fromAccount = new AccountBuilder().deposit(deposit).build();
        Account toAccount = new AccountBuilder().build();

        Money transfer = new Money(60);
        fromAccount.transfer(transfer, toAccount, true);

        assertEquals(deposit.minus(transfer), fromAccount.getBalance());
    }

    public void testShouldIncreaseBalanceOfTargetAccountOnTransfer() {
        Money initialDeposit = new Money(100);
        Account fromAccount = new AccountBuilder().deposit(initialDeposit).build();
        Account toAccount = new AccountBuilder().deposit(initialDeposit).build();

        Money amountToTransfer = new Money(60);
        fromAccount.transfer(amountToTransfer, toAccount, true);

        assertEquals(initialDeposit.plus(amountToTransfer), toAccount.getBalance());
    }

    public void testShouldNotReduceBalanceIfTransferIsNotAllowed() {
        Money initialBalance = new Money(100);
        Account fromAccount = new AccountBuilder().deposit(initialBalance).policy(new AlwaysFailsPolicy()).build();
        Account toAccount = new AccountBuilder().deposit(initialBalance).build();
        fromAccount.transfer(new Money(120), toAccount, true);
        assertEquals(initialBalance, fromAccount.getBalance());
        assertEquals(initialBalance, toAccount.getBalance());
    }

    public void testShouldBeAbleToGetCustomer() {
        Customer customer = bob;
        Account account = new AccountBuilder().customer(customer).build();
        assertEquals(customer, account.getOwner());
    }

    public void testShouldAssignAccountToCustomer() {
        Account account = new AccountBuilder().customer(bob).build();
        Set<Account> accounts = bob.getAccounts();
        assertTrue(accounts.contains(account));
    }

    public void testShouldPutAccountNumberIntoToString() {
        Account account = new AccountBuilder().build();
        assertTrue(account.toString().contains(account.number().toString()));
    }

    public void testShouldbeAbleToGetTheInterestRate() {
        AccountType rate = new AccountType(new AccountTypeName("Current"), new Percentage(10.0), new Money(0));
        Account account = new AccountBuilder().interestRate(rate).build();
        assertEquals(rate, account.getAccountType());
    }

    public void testShouldAssignAccountNameToAccount() {
        Account account = new AccountBuilder().name("myAccount").build();
        assertEquals(new AccountName("myAccount"), account.getName().force());
    }

    public void testCanWithdrawMoneyThatDoesNotExceedBankLimit() {
        Account account = new AccountBuilder().deposit(new Money(2000)).build();
        account.withdraw(new Money(1000));
        assertEquals(new Money(1000), account.getBalance());
    }

    public void testMoneyExceedingBankLimitCanBeTransferredSuccessfullyBetweenTwoAccountsOfTheSameOwner() {
        Customer customer = new CustomerBuilder().build();
        Account fromAccount = new AccountBuilder().customer(customer).deposit(new Money(2000)).build();
        Account toAccount = new AccountBuilder().customer(customer).build();
        fromAccount.transfer(new Money(1500), toAccount, true);
        assertEquals(new Money(1500), toAccount.getBalance());
    }

    public void testMoneyExceedingBankLimitCannotBeTransferredSuccessfullyBetweenAccountsOfDifferentOwners() {
        OnlinePaymentLimitPolicy onlinePolicy = new OnlinePaymentLimitPolicy(new Money(1000));

        Account fromAccount = new AccountBuilder().policy(onlinePolicy).customer(bob).build();
        fromAccount.deposit(new Money(2000));

        Account toAccount = new AccountBuilder().customer(alice).build();
        Money transfer = new Money(1500);
        fromAccount.transfer(transfer, toAccount, true);
        assertEquals(new Money(0), toAccount.getBalance());
    }

    public void testShouldNotBeAbleToCloseAnAccountWithMoneyInIt() {
        Account account = new AccountBuilder().deposit(new Money(100)).build();
        try {
            account.close();
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Account " + account + " has " + account.getBalance() + " in it so it cannot be closed.",
                    e.getMessage());
        }
    }

    public void testShouldBeAbleToCloseAnAccountWithNoMoneyInIt() {
        Account account = new AccountBuilder().withZeroBalance().build();
        // Should not throw an exception.
        account.close();
    }

    public void testShouldRemoveAccountFromCustomerWhenClosingIt() {
        Customer customer = new CustomerBuilder().build();
        Account account = new AccountBuilder().customer(customer).withZeroBalance().build();
        account.close();
        assertFalse(customer.getAccounts().contains(account));
    }

    public void testTransactionAddedWhenDepositIsAppliedToAccount() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Account account = new AccountBuilder().deposit(new Money(100)).clock(new TestClock(dateTime)).build();
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(new Money(100), dateTime));
        assertEquals(transactions, account.getStatement().getTransactions());
    }

    public void testTransactionAddedWhenMoneyIsRemovedFromAccount() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Account account = new AccountBuilder().deposit(new Money(100)).clock(new TestClock(dateTime)).build();
        account.withdraw(new Money(50));
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction(new Money(100), dateTime));
        transactions.add(new Transaction(new Money(-50), dateTime));
        assertEquals(transactions, account.getStatement().getTransactions());

    }

    public void testTransactionAddedToBothAccountsInATransfer() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Account fromAccount = new AccountBuilder().deposit(new Money(100)).clock(new TestClock(dateTime)).build();
        Account toAccount = new AccountBuilder().deposit(new Money(100)).clock(new TestClock(dateTime)).build();
        fromAccount.transfer(new Money(50), toAccount, false);

        List<Transaction> fromTransactions = new ArrayList<Transaction>();
        fromTransactions.add(new Transaction(new Money(100), dateTime));
        fromTransactions.add(new Transaction(new Money(-50), dateTime));

        List<Transaction> toTransactions = new ArrayList<Transaction>();
        toTransactions.add(new Transaction(new Money(100), dateTime));
        toTransactions.add(new Transaction(new Money(50), dateTime));

        assertEquals(fromTransactions, fromAccount.getStatement().getTransactions());
        assertEquals(toTransactions, toAccount.getStatement().getTransactions());

    }

    public void testShouldBeAbleToGetBalanceFromStatement() {
        DateTime dateTime = new DateTime(2008, 2, 1, 12, 0, 0, 0);
        Account account = new AccountBuilder().deposit(new Money(100)).clock(new TestClock(dateTime)).build();
        account.withdraw(new Money(40));
        Money balance = account.getBalance();
        assertTrue(new Money(60).equals(balance));
    }

    public void testShouldCalculateInterestAndReturnMoney() {
        Account account = new AccountBuilder().minBalance(new Money(200)).deposit(new Money(2000)).build();
        Money money = new Money(0, 55);
        assertEquals(money, account.calculateInterest());
    }

    public void testShouldReturnASuccessfulOutcomeIfTransferIsSuccessful() {
        Account fromAccount = new AccountBuilder().policy(new AlwaysSucceedsPolicy()).deposit(new Money(200)).build();
        Account toAccount = new AccountBuilder().build();
        Outcome outcome = fromAccount.transfer(new Money(1), toAccount, true);
        assertTrue(outcome.isSuccessful());
    }

    public void testShouldReturnAFailedOutcomeIfTransferIsUnSucceesful() {
        Account fromAccount = new AccountBuilder().policy(new AlwaysFailsPolicy()).deposit(new Money(200)).build();
        Account toAccount = new AccountBuilder().build();
        Outcome outcome = fromAccount.transfer(new Money(1), toAccount, true);
        assertFalse(outcome.isSuccessful());
    }

    public void testWithdrawalWithAPassingPolicyPasses() {
        Account fromAccount = new AccountBuilder().policy(new AlwaysSucceedsPolicy()).deposit(new Money(200)).build();
        Outcome outcome = fromAccount.withdraw(new Money(1));
        assertTrue(outcome.isSuccessful());
    }

    public void testWithdrawalWithAFailingPolicyFails() {
        Account fromAccount = new AccountBuilder().policy(new AlwaysFailsPolicy()).deposit(new Money(200)).build();
        Outcome outcome = fromAccount.withdraw(new Money(1));
        assertFalse(outcome.isSuccessful());
    }

}
