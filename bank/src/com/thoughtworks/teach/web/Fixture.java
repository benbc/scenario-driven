package com.thoughtworks.teach.web;

import org.picocontainer.PicoContainer;

public abstract class Fixture {
    private PicoContainer pico = null;

    public void setPico(PicoContainer pico) {
        this.pico = pico;
    }

    public abstract void apply();

    protected <T> T getComponent(Class<T> klass) {
        T component = pico.getComponent(klass);
        if (component == null) {
            throw new IllegalArgumentException("No component of class " + klass + " found.");
        }
        return component;
    }
}
