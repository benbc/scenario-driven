package com.thoughtworks.teach.web;

import junit.framework.TestCase;
import org.picocontainer.DefaultPicoContainer;

public class ControllerDispatcherTest extends TestCase {

    public void testShouldThrowExceptionIfControllerDoesNotExist() {
        ControllerDispatcher dispatcher = new ControllerDispatcher(new DefaultPicoContainer());
        try {
            dispatcher.dispatch(null, "Fish", null);
            fail();
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}
