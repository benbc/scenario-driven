package com.thoughtworks.teach.view.tester;

import com.gargoylesoftware.htmlunit.html.*;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Maybe;
import com.thoughtworks.teach.bank.util.Nothing;
import org.jaxen.JaxenException;

import java.util.List;

public class FormTester {
    private final HtmlForm htmlForm;

    public FormTester(HtmlForm htmlForm) {
        this.htmlForm = htmlForm;
    }

    public Maybe<HtmlSelect> getDropdown(String name) {
        try {
            List result = htmlForm.getByXPath("//select[@name='" + name + "']");
            if (result.size() == 1) {
                return new Just<HtmlSelect>((HtmlSelect) result.get(0));
            } else {
                return new Nothing<HtmlSelect>();
            }
        } catch (JaxenException e) {
            throw new RuntimeException(e);
        }
    }

    public HtmlTextInput getTextbox(String name) {
        return (HtmlTextInput) htmlForm.getInputByName(name);
    }

    public HtmlTextArea getTextarea(String name) {
        return htmlForm.getTextAreaByName(name);
    }

    public HtmlPage submit() {
        try {
            List result = htmlForm.getByXPath("//input[@type='submit']");
            HtmlSubmitInput submitButton = (HtmlSubmitInput) result.get(0);
            return (HtmlPage) submitButton.click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
}
