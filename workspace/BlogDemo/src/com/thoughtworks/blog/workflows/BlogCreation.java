package com.thoughtworks.blog.workflows;

import com.thoughtworks.blog.pages.HomePage;
import com.thoughtworks.blog.pages.NewBlogEntryPage;
import com.thoughtworks.blog.pages.PreviewPage;
import com.thoughtworks.blog.pages.PublishPage;

public class BlogCreation {

	private HomePage homePage;
	private NewBlogEntryPage newBlogEntryPage;

    public BlogCreation(HomePage homePage) {
        this.homePage = homePage;
    }

    public void createANewBlogEntry() {
        newBlogEntryPage = homePage.goToNewBlogEntry(this);
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
