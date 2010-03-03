package com.thoughtworks.teach.web;

import org.picocontainer.PicoContainer;
import org.picocontainer.ComponentAdapter;
import org.picocontainer.ParameterName;
import org.picocontainer.PicoVisitor;

import java.util.List;
import java.util.Collection;

public class StubPicoContainer implements PicoContainer {

    public Object getComponent(Object o) {
        return null;
    }

    public <T> T getComponent(Class<T> tClass) {
        return null;
    }

    public List getComponents() {
        return null;
    }

    public PicoContainer getParent() {
        return null;
    }

    public ComponentAdapter<?> getComponentAdapter(Object o) {
        return null;
    }

    public <T> ComponentAdapter<T> getComponentAdapter(Class<T> tClass, ParameterName parameterName) {
        return null;
    }

    public Collection<ComponentAdapter<?>> getComponentAdapters() {
        return null;
    }

    public <T> List<ComponentAdapter<T>> getComponentAdapters(Class<T> tClass) {
        return null;
    }

    public <T> List<T> getComponents(Class<T> tClass) {
        return null;
    }

    public void accept(PicoVisitor picoVisitor) {
    }
}
