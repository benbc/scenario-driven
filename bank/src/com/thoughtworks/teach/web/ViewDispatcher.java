package com.thoughtworks.teach.web;

import com.thoughtworks.teach.bank.model.Money;
import com.thoughtworks.teach.bank.util.Just;
import com.thoughtworks.teach.bank.util.Nothing;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.picocontainer.PicoContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ViewDispatcher {
    private final StringTemplateGroup templates;
    private final PicoContainer container;

    public ViewDispatcher(PicoContainer container, StringTemplateGroup templates) {
        this.container = container;
        this.templates = templates;
        templates.registerRenderer(Just.class, new MaybeRenderer());
        templates.registerRenderer(Nothing.class, new MaybeRenderer());
        templates.registerRenderer(Money.class, new MoneyRenderer());
        templates.registerRenderer(Boolean.class, new BooleanRenderer());
    }

    public void dispatch(HttpServletResponse response, String name, Map<String, String> requestParams) {
        StringTemplate template = getTemplate(name);
        Map<String, Object> templateParams = getView(name).process(requestParams);
        set(templateParams, template);
        write(template, response);
    }

    private void set(Map<String, Object> templateParams, StringTemplate template) {
        for (Map.Entry<String, Object> param : templateParams.entrySet()) {
            template.setAttribute(param.getKey(), param.getValue());
        }
    }

    private void write(StringTemplate template, HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            response.getWriter().write(template.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private StringTemplate getTemplate(String name) {
        return templates.getInstanceOf(name);
    }

    private View getView(String name) {
        String fullName = ClassRegistry.buildViewKey(name);
        View view = (View) container.getComponent(fullName);
        if (view == null) {
            view = new DefaultView();
        }
        return view;
    }
}
