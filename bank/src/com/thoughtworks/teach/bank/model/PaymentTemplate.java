package com.thoughtworks.teach.bank.model;

public class PaymentTemplate {

    private Money amountToBePaid;
    private final Account fromAccount;
    private final Account toAccount;
    private final PaymentTemplateID id = new PaymentTemplateID();

    public PaymentTemplate(Account fromAccount, Account toAccount, Money amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amountToBePaid = amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public Money getAmount() {
        return amountToBePaid;
    }

    public String getName() {
         return toAccount.getOwner().getName().toString()+ " " + amountToBePaid.toString();
    }

    public PaymentTemplateID getId() {
        return id;
    }

    public String toString() {
        return getName();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PaymentTemplate)) {
            return false;
        }
        PaymentTemplate that = (PaymentTemplate) obj;
        return this.amountToBePaid.equals(that.amountToBePaid) &&
                this.fromAccount.equals(that.fromAccount) &&
                this.toAccount.equals(that.toAccount);
    }
}
