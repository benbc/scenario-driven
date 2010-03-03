package com.thoughtworks.teach.web;

import org.antlr.stringtemplate.StringTemplateGroup;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.jetty.webapp.WebAppContext;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class WebServer {
    private final MutablePicoContainer pico = new DefaultPicoContainer();
    private final Server server;
    private final ClassRegistry registry;
    private final Fixture fixture;

    public WebServer(ClassRegistry registry, Fixture fixture, int portNumber) {
        server = new Server(portNumber);
        this.registry = registry;
        this.fixture = fixture;
    }

    public void run() {
        configurePico();
        configureServer();
        applyFixture();
        startServer();
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void applyFixture() {
        fixture.setPico(pico);
        fixture.apply();
    }

    private void startServer() {
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void configureServer() {
        WebAppContext context = new WebAppContext("web", "/");
        Dispatcher dispatcher = new Dispatcher(new ViewDispatcher(pico, templates()), new ControllerDispatcher(pico));
        context.addServlet(new ServletHolder(dispatcher), "/*");
        server.addHandler(context);
    }

    private StringTemplateGroup templates() {
        StringTemplateGroup templates = new StringTemplateGroup("templates", "web/view");
        templates.setRefreshInterval(0);
        return templates;
    }

    private void configurePico() {
        registry.setPico(pico);
        registry.register();
    }
}
