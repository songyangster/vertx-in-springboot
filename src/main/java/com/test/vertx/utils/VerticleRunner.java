package com.test.vertx.utils;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;

import java.util.function.Consumer;

public class VerticleRunner {


    public static void runForJava(String prefix, Class clazz, boolean clustered) {

        // Vertx Configurations
        VertxOptions vertxOptions = new VertxOptions();
        vertxOptions.setClustered(clustered).setEventLoopPoolSize(500);
        vertxOptions.setMetricsOptions(new DropwizardMetricsOptions().setEnabled(true));
        vertxOptions.setMetricsOptions(new DropwizardMetricsOptions().setJmxEnabled(true));
        runForJava(prefix, clazz, vertxOptions);
    }

    public static void runForJava(String prefix, Class clazz, VertxOptions options) {
        String exampleDir = prefix + clazz.getPackage().getName().replace(".", "/");
        setUpVertxRunner(exampleDir, clazz.getName(), options);
    }

    public static void setUpVertxRunner(String exampleDir, String verticleID, VertxOptions options) {
        System.setProperty("test", exampleDir);
        Consumer<Vertx> runner = vertx -> {
            try {
                vertx.deployVerticle(verticleID);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        if (options.isClustered()) {
            Vertx.clusteredVertx(options, res -> {
                if (res.succeeded()) {
                    Vertx vertx = res.result();
                    runner.accept(vertx);
                } else {
                    res.cause().printStackTrace();
                }
            });
        } else {
            Vertx vertx = Vertx.vertx(options);
            runner.accept(vertx);
        }
    }
}