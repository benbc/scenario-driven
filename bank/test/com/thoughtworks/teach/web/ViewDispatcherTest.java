package com.thoughtworks.teach.web;

import junit.framework.TestCase;

import java.util.HashMap;

public class ViewDispatcherTest extends TestCase {

    public void xtestCreatesADefaultViewIfNoViewExists() throws Exception {
        ViewDispatcher viewDispatcher = new ViewDispatcher(new StubPicoContainer(), new StubTemplates());
        viewDispatcher.dispatch(new StubHttpResponse(), "TransferFailure", new HashMap<String, String>());
    }

    public void testShouldThrowAnExceptionIfStringTemplateDoesNotExist() throws Exception {
        ViewDispatcher viewDispatcher = new ViewDispatcher(new StubPicoContainer(), new StubTemplates());
        try {
            viewDispatcher.dispatch(null, "DoesNotExist", new HashMap<String, String>());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Can't find template DoesNotExist.st");
        }
    }
}
