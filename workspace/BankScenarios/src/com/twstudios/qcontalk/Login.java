package com.twstudios.qcontalk;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class Login {

	private TwistSelenium selenium;

	public Login(TwistSelenium selenium) {
		this.selenium = selenium;
	}

	public void logsIn(String nickname) throws Exception {
		selenium.open("http://localhost:8080/Login");
		selenium.type("customer", nickname);
		selenium.click("//input[@value='Login']");
	}
}
