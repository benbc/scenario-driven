package com.thoughtworks.teach.view.tester;

import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DropDownTester {
    private final HtmlSelect dropdown;

    public DropDownTester(HtmlSelect dropdown) {
        this.dropdown = dropdown;
    }

    public Set<String> options() {
        Set<String> options = new HashSet<String>();
        List list = dropdown.getOptions();
        for (Object option : list) {
            options.add(((HtmlOption) option).asText());
        }
        return options;
    }

    public void select(HtmlOption optionToSelect) {
        dropdown.setSelectedAttribute(optionToSelect, true);
    }

    public void select(String optionToSelect) {
        for (Object option : dropdown.getOptions()) {
            HtmlOption htmlOption = (HtmlOption) option;
            if (htmlOption.asText().contains(optionToSelect)) {
                dropdown.setSelectedAttribute(htmlOption, true);
            }
        }
    }

    public HtmlOption option(int index) {
        return (HtmlOption) dropdown.getOptions().get(index - 1);
    }

}
