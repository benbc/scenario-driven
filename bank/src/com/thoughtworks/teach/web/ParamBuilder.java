package com.thoughtworks.teach.web;

import java.util.Map;

public class ParamBuilder {

    private final String name;

    public ParamBuilder(String name) {
        this.name = name;
    }

    public Param value(Object value) {
        return new Param(name, value);
    }

    public Param from(Map<String, String> oldParameters) {
        return new Param(name, oldParameters.get(name));
    }

    public static ParamBuilder name(String name) {
        return new ParamBuilder(name);
    }
}
