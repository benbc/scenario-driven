package com.thoughtworks.blog.tables;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;


public class LoginTable {
    private final TwistSelenium selenium;

    public LoginTable(TwistSelenium selenium) {
        this.selenium = selenium;
        selenium.open("/blog");
    }

    public void setPassword(String password) {
        selenium.type("j_password", password);
    }

    public void setUsername(String username) {
    	selenium.type("j_username", username);
    }

    public boolean loggedIn() throws InterruptedException {
        selenium.submit("loginForm");
        
        boolean loggedIn = selenium.isTextPresent("Logout");

        if (loggedIn) {
            selenium.click("link=Logout");
        }

        return loggedIn;
    }

}
