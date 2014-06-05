package com.tengen;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;

public class HelloWorldMongoDbSparkFreemarkerStyle {

    public static void main(String[] args) throws IOException, TemplateException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldMongoDbSparkFreemarkerStyle.class, "/");
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        final DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                final StringWriter stringWriter = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    final DBObject document = collection.findOne();
                    helloTemplate.process(document, stringWriter);
                } catch (Exception e) {
                    halt(500);
                }
                return stringWriter;
            }
        });
    }
}
