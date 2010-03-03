package com.thoughtworks.teach.web;

import java.util.HashMap;
import java.util.Map;

public class DefaultView implements View {
    public Map<String, Object> process(Map<String, String> requestParams) {
        Map<String, Object> templateParams = new HashMap<String, Object>();
        for (Map.Entry<String, String> entry: requestParams.entrySet()){
            templateParams.put(entry.getKey(), entry.getValue());
        }
        return templateParams;
    }
}
