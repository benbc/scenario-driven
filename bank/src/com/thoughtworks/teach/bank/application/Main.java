package com.thoughtworks.teach.bank.application;

import com.thoughtworks.teach.web.WebServer;

public class Main {
    public static void main(String[] args) {
        new WebServer(new BankRegistry(), new BankFixture(), 8080).run();
    }
}
