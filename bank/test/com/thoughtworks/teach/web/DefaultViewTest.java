package com.thoughtworks.teach.web;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class DefaultViewTest extends TestCase {
    public void testParametersArePassedToDefaultView() {
        //Create a default view and test that it has passed parameters
        View defaultView = new DefaultView();
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("name", "Anne");
        requestParams.put("customer", "Seema");

        Map<String, Object> templateParams = defaultView.process(requestParams);
        assertEquals(2, templateParams.size());
        assertTrue(templateParams.containsKey("name"));
        assertTrue(templateParams.containsValue("Anne"));
        assertTrue(templateParams.containsKey("customer"));
        assertTrue(templateParams.containsValue("Seema"));
    }
    
}

