package com.thoughtworks.blog.workflows;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class HomePage {

	private final TwistSelenium selenium;

	public HomePage(TwistSelenium selenium) {
		this.selenium = selenium;
	}

	public NewBlogEntryPage goToNewBlogEntry(BlogCreation blogCreation) {
		selenium.click("link=New blog entry");
		return new NewBlogEntryPage(selenium); 
	}

	public boolean hasText(String text) {
		return selenium.isTextPresent(text);
	}
}
