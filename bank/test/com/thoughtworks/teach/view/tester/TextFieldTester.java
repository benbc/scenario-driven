package com.thoughtworks.teach.view.tester;

import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TextFieldTester {
    private final ClickableElement textInput;

    public TextFieldTester(ClickableElement textInput) {
        this.textInput = textInput;
    }

    public boolean isReadonly() {
        return textInput.getAttributeValue("readonly").equals("true");
    }

    public String text() {
        return textInput.asText();
    }

    public void value(String value) {
       if(textInput instanceof HtmlTextArea){
           HtmlTextArea text = (HtmlTextArea) textInput;
           text.setText(value);
       }else if(textInput instanceof HtmlTextInput){
           HtmlTextInput text = (HtmlTextInput)textInput;
           text.setValueAttribute(value);
       }
    }
}
