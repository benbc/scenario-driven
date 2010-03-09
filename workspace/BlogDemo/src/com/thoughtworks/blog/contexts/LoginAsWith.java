package com.thoughtworks.blog.contexts;

import com.thoughtworks.blog.pages.LoginPage;

public class LoginAsWith {

    private final LoginPage loginPage;

    public LoginAsWith(LoginPage loginPage) {
        this.loginPage = loginPage;

    }

    public void setUp(String user, String password) {
        loginPage.enterUsername(user);
        loginPage.enterPassword(password);
        loginPage.submit();
    }

    public void tearDown(String user, String pass) {
        loginPage.logoutFromTheBlog();
    }
}
