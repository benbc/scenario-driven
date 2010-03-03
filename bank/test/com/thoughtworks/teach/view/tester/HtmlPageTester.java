package com.thoughtworks.teach.view.tester;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.net.URL;

public class HtmlPageTester {
    private final HtmlPage htmlPage;

    public HtmlPageTester(HtmlPage htmlPage) {
        this.htmlPage = htmlPage;
    }

    public URL currentUrl() {
        return htmlPage.getWebResponse().getUrl();
    }

    public boolean displays(String... texts) {
        String pageText = htmlPage.asText();
        for (String text : texts) {
            if(!pageText.contains(text)) return false;
        }
        return true;
    }


}
