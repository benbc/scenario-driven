package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertTrue;

public class OnTheHomepage {

	private final HomePage homePage;

	public OnTheHomepage(HomePage homePage) {
		this.homePage = homePage;
	}

	public void weSeeAnEntryTitledWithContent(String title, String content) {
        assertTrue(homePage.hasText(title));
        assertTrue(homePage.hasText(content));
	}
}
