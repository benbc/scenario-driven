package com.thoughtworks.blog.pages;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class PublishPage {

	private final TwistSelenium selenium;

	public PublishPage(TwistSelenium selenium) {
		this.selenium = selenium;
	}

	public HomePage publish() {
		selenium.click("//button[@value='Publish']");
		return new HomePage(selenium);
	}
}
