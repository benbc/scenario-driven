package com.thoughtworks.blog.pages;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class LoginPage {

    private TwistSelenium selenium;

    public LoginPage(TwistSelenium selenium) {
        this.selenium = selenium;
        selenium.open("/blog");
    }

    public void enterUsername(String name) {
        selenium.type("j_username", name);
    }
    
    public void enterPassword(String password) {
        selenium.type("j_password", password);
    }
    
    public void submit() {
        selenium.submit("loginForm");
    }

    public void logoutFromTheBlog() {
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
        selenium.click("link=Logout");
    }
}
