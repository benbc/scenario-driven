package com.thoughtworks.teach.web;

import org.picocontainer.MutablePicoContainer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ClassRegistry {
    private MutablePicoContainer pico = null;

    public abstract void register();

    protected void addController(Class<?> controller) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)Controller");
        Matcher matcher = pattern.matcher(controller.getSimpleName());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("This doesn't seem to be a controller: " + controller);
        }
        pico.addComponent(buildControllerKey(matcher.group(1)), controller);
    }

    protected void addView(Class<?> view) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)View");
        Matcher matcher = pattern.matcher(view.getSimpleName());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("This doesn't seem to be a view: " + view);
        }
        pico.addComponent(buildViewKey(matcher.group(1)), view);
    }

    protected void addRepository(Object repository) {
        pico.addComponent(repository);
    }

    protected void addSingleton(Object singleton) {
        pico.addComponent(singleton);
    }

    public void setPico(MutablePicoContainer pico) {
        this.pico = pico;
    }

    static String buildControllerKey(String name) {
        return name.toLowerCase() + "-controller";
    }

    static String buildViewKey(String name) {
        return name.toLowerCase() + "-view";
    }
}
