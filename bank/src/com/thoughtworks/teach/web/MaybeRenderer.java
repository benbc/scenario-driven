package com.thoughtworks.teach.web;

import com.thoughtworks.teach.bank.util.Maybe;
import org.antlr.stringtemplate.AttributeRenderer;

public class MaybeRenderer implements AttributeRenderer {
    @SuppressWarnings({"unchecked"})
    public String toString(Object o) {
        Maybe maybe = (Maybe) o;
        if (maybe.hasValue()) {
            return maybe.force().toString();
        } else {
            return "";
        }
    }
}
