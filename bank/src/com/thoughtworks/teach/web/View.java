package com.thoughtworks.teach.web;

import java.util.Map;

public interface View {
    Map<String, Object> process(Map<String, String> requestParams);
}
