package com.thoughtworks.teach.view.pageDrivers;

import com.thoughtworks.teach.bank.model.Account;
import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.view.tester.DropDownTester;
import com.thoughtworks.teach.view.tester.FormTester;
import com.thoughtworks.teach.view.tester.TextFieldTester;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CustomerHomepage extends DefaultPage {
    public static String mainUrl = "/CustomerHomepage";
    public FormTester transferForm;

    public CustomerHomepage(Customer customer) {
        super(mainUrl + "?customer=" + customer.getNickName());
        transferForm = new FormTester(getForm("makeTransfer").force());
    }

    public void selectTransferFrom(Account fromAccount) {
        new DropDownTester(transferForm.getDropdown("from").force()).select(fromAccount.number().toString());
    }

    public void selectTransferTo(Account toAccount) {
        new DropDownTester(transferForm.getDropdown("to").force()).select(toAccount.number().toString());
    }

    public void transferAmount(String transferAmount) {
        new TextFieldTester(transferForm.getTextbox("amount")).value(transferAmount);        
    }

    public HtmlPage makeTransfer() {

       return transferForm.submit();
    }
}
