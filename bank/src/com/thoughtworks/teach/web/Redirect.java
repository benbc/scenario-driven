package com.thoughtworks.teach.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Redirect {
    private final List<Param> params;
    private final Class<? extends View> view;

    public Redirect(Class<? extends View> view, Param... params) {
        this.view = view;
        this.params = Arrays.asList(params);
    }

    public void on(HttpServletResponse response) {
        try {
            response.sendRedirect(buildPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildPath() {
        return addParams(name());
    }

    private String name() {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)View");
        Matcher matcher = pattern.matcher(view.getSimpleName());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("This doesn't seem to be a view: " + view);
        }
        return matcher.group(1);
    }

    private String addParams(String path) {
        if (params.isEmpty()) {
            return path;
        }
        return path + "?" + Utils.join(params, "&");
    }

    public String toString() {
        return name() + "[" + Utils.join(params, ",") + "]";
    }

    public boolean equals(Object o) {
        if (!(o instanceof Redirect)) {
            return false;
        }
        Redirect that = (Redirect) o;
        return that.view.equals(this.view) && that.params.equals(this.params);
    }

    public int hashCode() {
        return view.hashCode();
    }
}
