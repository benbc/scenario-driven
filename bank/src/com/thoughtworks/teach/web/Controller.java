package com.thoughtworks.teach.web;

import java.util.Map;

public interface Controller {
    Redirect execute(Map<String, String> parameters);
}
