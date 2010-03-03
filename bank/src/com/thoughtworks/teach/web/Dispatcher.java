package com.thoughtworks.teach.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dispatcher extends HttpServlet {
    private static final Pattern PATTERN = Pattern.compile("/([a-zA-Z]+)");
    private final ViewDispatcher viewDispatcher;
    private final ControllerDispatcher controllerDispatcher;

    public Dispatcher(ViewDispatcher viewDispatcher, ControllerDispatcher controllerDispatcher) {
        this.viewDispatcher = viewDispatcher;
        this.controllerDispatcher = controllerDispatcher;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        viewDispatcher.dispatch(response, parseName(request), params(request));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        controllerDispatcher.dispatch(response, parseName(request), params(request));
    }

    private Map<String, String> params(HttpServletRequest request) {
        HashMap<String, String> params = new HashMap<String, String>();
        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            params.put(name, request.getParameter(name));
        }
        return params;
    }

    private static String parseName(HttpServletRequest request) {
        Matcher matcher = PATTERN.matcher(request.getPathInfo());
        matcher.matches();
        return matcher.group(1);
    }
}
