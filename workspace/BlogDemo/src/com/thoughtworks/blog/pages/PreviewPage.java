package com.thoughtworks.blog.pages;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class PreviewPage {
	
	private final TwistSelenium selenium;

	public PreviewPage(TwistSelenium selenium) {
		this.selenium = selenium;
	}
	
	public PublishPage publish() {
		selenium.click("//button[@value='Publish']");
		return new PublishPage(selenium);
	}
}
