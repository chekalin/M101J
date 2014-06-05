package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldSparkFreemarkerStyle {

    public static void main(String[] args) throws IOException, TemplateException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");


        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                final StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    final Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Stas");
                    helloTemplate.process(helloMap, stringWriter);
                } catch (Exception e) {
                    halt(500);
                }
                return stringWriter;
            }
        });
    }
}
