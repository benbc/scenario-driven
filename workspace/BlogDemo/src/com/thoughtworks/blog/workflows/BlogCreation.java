package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertTrue;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class BlogCreation {

    private String blogTitle = "My Blog";
    private String blogContent = "my blog text.";

    private static final String CHANGED_BLOG_TITLE = "My Blog (Updated)";
    private static final String CHANGED_BLOG_CONTENT = "my updated blog text.";
    TwistSelenium selenium;
	private HomePage homePage;
	private NewBlogEntryPage newBlogEntryPage;

    public BlogCreation(TwistSelenium selenium, HomePage homePage) {
        this.selenium = selenium;
        this.homePage = homePage;
    }

    public void createANewBlogEntryWithSomeSampleContent() {
        createANewBlogEntry();
        addSomeSampleContent();
    }

    public void verifyThatTheContentAppearsOnTheHomePage() {
        selenium.click("link=Home");
        assertTrue(selenium.isTextPresent(blogTitle));
        assertTrue(selenium.isTextPresent(blogContent));
    }

    public void editTheBlogEntryContent() {
        selenium.click("link=" + blogTitle);
        selenium.click("//button[@value='Edit']");
        selenium.type("title", CHANGED_BLOG_TITLE);
        selenium.type("name=body", CHANGED_BLOG_CONTENT);
        selenium.click("//button[@value='Save']");
    }

    public void verifyThatTheEditedContentAppearsOnTheHomePage() {
        selenium.click("link=Home");
        assertTrue(selenium.isTextPresent(CHANGED_BLOG_TITLE));
        assertTrue(selenium.isTextPresent(CHANGED_BLOG_CONTENT));
    }

    public void createANewBlogEntry() {
        newBlogEntryPage = homePage.goToNewBlogEntry(this);
    }

	public void addSomeSampleContent() {
        selenium.type("title", blogTitle);
        selenium.type("name=body", blogContent);
        selenium.click("//button[@value='Save']");
    }

	public void enterATitle(String title) throws Exception {
		newBlogEntryPage.enterTitle(title);
	}

	public void enterABody(String body) throws Exception {
		newBlogEntryPage.enterBody(body);
	}

	public void saveAndPublishTheEntry() throws Exception {
		PreviewPage previewPage = newBlogEntryPage.save();
		PublishPage publishPage = previewPage.publish();
		homePage = publishPage.publish();
	}
}
