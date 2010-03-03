package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.bank.model.Bank;
import com.thoughtworks.teach.bank.model.CustomerRepository;
import com.thoughtworks.teach.bank.model.SystemClock;
import com.thoughtworks.teach.web.ClassRegistry;

public class BankRegistry extends ClassRegistry {

    public void register() {
        domain();
        application();
    }

    private void application() {
        addController(CreateAccountController.class);
        addController(AddCustomerController.class);
        addController(MakeTransferController.class);
        addController(MakePaymentController.class);
        addController(FindAccountController.class);
        addController(SearchNamesController.class);
        addController(LoginController.class);
        addController(ChangeInterestRatesController.class);
        addController(PersonalDetailsController.class);
        addController(FindAccountDetailsController.class);
        addController(FindCustomerAccountByNameController.class);
        addController(BankAdminController.class);
        addController(DailyProcessingController.class);
        addController(AddAccountTypeController.class);
        addController(CloseAccountController.class);
        addController(ExistingPaymentTemplatesController.class);
        addController(MakeDepositController.class);
        addController(MakeWithdrawalController.class);
        addController(DeactivateCustomerController.class);
        addController(ReactivateCustomerController.class);
        addView(CreateAccountView.class);
        addView(AdminHomepageView.class);
        addView(CustomerHomepageView.class);
        addView(CustomerListView.class);
        addView(FoundAccountView.class);
        addView(SearchNamesView.class);
        addView(MatchingCustomersView.class);
        addView(MakePaymentView.class);
        addView(CustomerAccountDetailsView.class);
        addView(CustomerDetailsView.class);
        addView(PersonalDetailsView.class);
        addView(AccountTypesView.class);
        addView(BankAdminView.class);
        addView(FoundCustomerAccountView.class);
        addView(AccountServicesView.class);
    }

    private void domain() {
        CustomerRepository customers = new CustomerRepository();
        addRepository(customers);
        addSingleton(new Bank(customers, new CustomerRepository(), new SystemClock()));
    }
}
