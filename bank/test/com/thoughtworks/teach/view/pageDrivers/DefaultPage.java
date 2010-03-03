package com.thoughtworks.teach.view.pageDrivers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import com.thoughtworks.teach.view.tester.HtmlPageTester;
import org.jaxen.JaxenException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DefaultPage {
    protected HtmlPage htmlPage;
    private final String serverUrl = "http://localhost:8088";
    private HtmlPageTester page;

    public DefaultPage(String pageUrl) {
        WebClient browser = new WebClient();
        try {
            htmlPage = (HtmlPage) browser.getPage(serverUrl + pageUrl);
            page = new HtmlPageTester(htmlPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HtmlPageTester page() {
        return page;
    }

    public Maybe<HtmlForm> getForm(String actionName) {
        try {
            List forms = htmlPage.getByXPath("/html/body/form[@action='" + actionName + "']");
            if (forms.size() == 1) {
                return new Just<HtmlForm>((HtmlForm) forms.get(0));
            } else {
                return new Nothing<HtmlForm>();
            }
        } catch (JaxenException e) {
            throw new RuntimeException(e);
        }
    }

    public HtmlPageTester clickLink(String mainUrl) {
        Maybe<HtmlAnchor> htmlAnchorMaybe = getLink(mainUrl);
        try {
            HtmlAnchor link = htmlAnchorMaybe.force();
            return new HtmlPageTester(((HtmlPage) link.click()));
        } catch (IOException e) {
            throw new RuntimeException("Could not find '" + mainUrl + "' link");
        }
    }

    private Maybe<HtmlAnchor> getLink(String mainUrl) {
        List anchors = htmlPage.getAnchors();
        for (Object element : anchors) {
            HtmlAnchor anchor = (HtmlAnchor) element;
            if (anchor.getHrefAttribute().toLowerCase().contains(mainUrl.toLowerCase())) {
                return new Just<HtmlAnchor>(anchor);
            }
        }
        return new Nothing<HtmlAnchor>();
    }

    public URL currentUrl() {
        return page.currentUrl();
    }

    public boolean displays(String... texts) {
        return page.displays(texts);
    }
    
    public void dump() {
    	System.out.println(htmlPage.asXml());
    }
}
