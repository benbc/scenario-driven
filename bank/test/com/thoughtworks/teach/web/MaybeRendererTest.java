package com.thoughtworks.teach.web;

import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Nothing;
import junit.framework.TestCase;

public class MaybeRendererTest extends TestCase {
    private final MaybeRenderer renderer = new MaybeRenderer();

    public void testShouldRenderNothingAsEmptyString() {
        assertEquals("", renderer.toString(new Nothing<Integer>()));
    }

    public void testShouldRenderJustAsToStringOfValue() {
        assertEquals("55", renderer.toString(new Just<Integer>(55)));
    }
}
