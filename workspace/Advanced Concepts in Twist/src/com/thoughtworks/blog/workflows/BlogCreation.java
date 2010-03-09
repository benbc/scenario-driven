package com.thoughtworks.blog.workflows;

import static junit.framework.Assert.assertTrue;

import com.thoughtworks.twist.driver.selenium.TwistSelenium;

public class BlogCreation {

    private String blogTitle = "My Blog";
    private String blogContent = "my blog text.";

    private static final String CHANGED_BLOG_TITLE = "My Blog (Updated)";
    private static final String CHANGED_BLOG_CONTENT = "my updated blog text.";
    private TwistSelenium selenium;

    public BlogCreation(TwistSelenium selenium) {
        this.selenium = selenium;
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
        selenium.click("link=New blog entry");
    }

    public void addSomeSampleContent() {
        selenium.type("title", blogTitle);
        selenium.type("name=body", blogContent);
        selenium.click("//button[@value='Save']");
    }
}
