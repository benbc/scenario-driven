package com.thoughtworks.teach.web;

import static com.thoughtworks.teach.web.ParamBuilder.name;
import junit.framework.TestCase;

import java.util.Map;

public class RedirectTest extends TestCase {

    public void testShouldNotBeEqualsIfViewsAreDifferent() {
        assertFalse(new Redirect(AView.class).equals(new Redirect(AnotherView.class)));
    }

    public void testShouldBeEqualIfViewsAreTheSame() {
        assertEquals(new Redirect(AView.class), new Redirect(AView.class));
    }

    public void testShouldNotBeEqualIfParametersAreDifferent() {
        Redirect withParams = new Redirect(AView.class, name("ping").value("pong"));
        Redirect withoutParams = new Redirect(AView.class);
        assertFalse(withParams.equals(withoutParams));
    }

    public void testShouldBeEqualIfParametersAreTheSame() {
        assertEquals(new Redirect(AView.class, name("foo").value("bar"), name("bla").value("blue")), new Redirect(AView.class, name("foo").value("bar"), name("bla").value("blue")));
    }

    public void testShouldDisplayViewNameInToString() {
        assertTrue(new Redirect(AView.class).toString().contains("A"));
    }

    public void testShouldDisplayParametersInToString() {
        assertTrue(new Redirect(AView.class, name("ping").value("pong")).toString().contains("[ping=pong]"));
    }

    private class AView implements View {
        public Map<String, Object> process(Map<String, String> requestParams) {
            return null;
        }
    }

    private class AnotherView implements View {
        public Map<String, Object> process(Map<String, String> requestParams) {
            return null;
        }
    }
}
