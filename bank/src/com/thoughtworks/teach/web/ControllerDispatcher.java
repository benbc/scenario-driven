package com.thoughtworks.teach.web;

import static com.thoughtworks.teach.web.ParamBuilder.name;
import org.picocontainer.PicoContainer;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.io.StringWriter;
import java.io.PrintWriter;

public class ControllerDispatcher {
    private final PicoContainer container;

    public ControllerDispatcher(PicoContainer container) {
        this.container = container;
    }

    public void dispatch(HttpServletResponse response, String name, Map<String, String> params) {
        Controller controller = getController(name);
        Redirect redirect;
        try {
            redirect = controller.execute(params);
        } catch (RuntimeException e) {
            redirect = new Redirect(GenericFailureView.class, name("message").value(e.getMessage()),
                    name("detail").value(stackTraceOf(e)));
        }
        redirect.on(response);
    }

    private String stackTraceOf(RuntimeException e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString().replace("\n", "<br>\n");
    }

    private Controller getController(String name) {
        String fullName = ClassRegistry.buildControllerKey(name);
        Controller controller = (Controller) container.getComponent(fullName);
        if (controller == null) {
            throw new IllegalArgumentException("There is no controller called " + fullName);
        }
        return controller;
    }
}
