package com.thoughtworks.teach.web;

import org.antlr.stringtemplate.AttributeRenderer;

public class MoneyRenderer implements AttributeRenderer {
    public String toString(Object o) {
        return o.toString().replace("£", "&pound;");
    }
}
