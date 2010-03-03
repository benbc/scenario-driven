package com.thoughtworks.teach.view.pageDrivers;

import com.thoughtworks.teach.bank.model.Customer;
import com.thoughtworks.teach.view.tester.FormTester;
import com.thoughtworks.teach.view.tester.TextFieldTester;

public class PersonalDetailsPage extends DefaultPage {
    private FormTester form;
    public static String mainUrl = "/PersonalDetails";

    public PersonalDetailsPage(Customer customer) {
        super(mainUrl + "?customer=" + customer.getNickName());
        form = new FormTester(getForm("personalDetails").force());

    }

    public TextFieldTester nameField() {
        return new TextFieldTester(form.getTextbox("name"));
    }

    public TextFieldTester nicknameField() {
        return new TextFieldTester(form.getTextbox("customer"));
    }

    public TextFieldTester addressField() {
        return new TextFieldTester(form.getTextarea("address"));
    }

    public void changeAddressFieldTo(String address) {
        addressField().value(address);
    }

    public TextFieldTester postcodeField() {
        return new TextFieldTester(form.getTextbox("postcode"));
    }

    public TextFieldTester phoneNumberField() {
        return new TextFieldTester(form.getTextbox("phoneNumber"));
    }

    public void saveChanges() {
        form.submit();
    }

    public void goBackToHomepage() {
        clickLink(CustomerHomepage.mainUrl);
    }
}
