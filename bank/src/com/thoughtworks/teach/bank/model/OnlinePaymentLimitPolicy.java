package com.thoughtworks.teach.bank.model;

public class OnlinePaymentLimitPolicy implements Policy {
    private Money bankLimit;

    public OnlinePaymentLimitPolicy(Money limit) {
        this.bankLimit = limit;
    }

    public Outcome isFulFilled(Transfer transfer) {
        boolean status = isOffline(transfer) || accountOwnersAreSame(transfer) || transferAmountDoesNotExceedsLimit(transfer);
        if (status)
            return new Outcome(Status.Successful, "");
        else return new Outcome(Status.Failed, "Online payments of more than " + bankLimit + " are not allowed.");
    }

    private boolean isOffline(Transfer transfer) {
        return !transfer.isOnline();
    }

    private boolean transferAmountDoesNotExceedsLimit(Transfer transfer) {
        return !bankLimit.lessThan(transfer.getAmountToTransfer());
    }

    private boolean accountOwnersAreSame(Transfer transfer) {
        if (!transfer.getToAccount().hasValue()) {
            return true;
        }
        Customer fromOwner = transfer.getFromAccount().force().getOwner();
        Customer toOwner = transfer.getToAccount().force().getOwner();
        return fromOwner.equals(toOwner);
    }

    public void setOnlineLimit(Money newOnlineLimit) {
        bankLimit = newOnlineLimit;
    }
}
