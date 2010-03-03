package com.thoughtworks.teach.bank.model;

public class MinimumBalancePolicy implements Policy {
    private Money minBalance;

    public MinimumBalancePolicy(Money minBalance) {
        this.minBalance = minBalance;
    }

    public Outcome isFulFilled(Transfer transfer) {
        if (!transferAmountLeavesAtLeastMinimumBalance(transfer)) {
            return new Outcome(Status.Failed, "The balance for account " + transfer.getFromAccount().force() +
                    " must not fall below " + minBalance);
        }
        return new Outcome(Status.Successful, "");
    }

    private boolean transferAmountLeavesAtLeastMinimumBalance(Transfer transfer) {
        Money fromAmount = transfer.getFromAccount().force().getBalance();
        Money transferAmount = transfer.getAmountToTransfer();
        return !fromAmount.minus(transferAmount).lessThan(minBalance);
    }

    public Money minBalance() {
        return minBalance;
    }
}
