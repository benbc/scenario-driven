package com.thoughtworks.teach.web;

import org.antlr.stringtemplate.AttributeRenderer;

public class BooleanRenderer implements AttributeRenderer {
    public String toString(Object o) {
        return o.equals(true) ? "Yes" : "No";
    }
}
