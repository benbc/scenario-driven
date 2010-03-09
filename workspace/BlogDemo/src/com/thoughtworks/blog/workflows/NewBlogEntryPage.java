package com.thoughtworks.blog.workflows;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class NewBlogEntryPage {

	private final TwistSelenium selenium;

	public NewBlogEntryPage(TwistSelenium selenium) {
		this.selenium = selenium;
	}

	public void enterTitle(String title) {
		selenium.type("title", title);
	}

	public void enterBody(String body) {
		selenium.type("name=body", body);
	}

	public PreviewPage save() {
        selenium.click("//button[@value='Save']");
        return new PreviewPage(selenium);
	}
}
