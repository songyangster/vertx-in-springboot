package com.test.vertx.utils;


public class Runner {

    private static final String CORE_EXAMPLES_DIR = "";
    private static final String CORE_EXAMPLES_JAVA_DIR = CORE_EXAMPLES_DIR + "/src/main/java/";

    public static void runClusteredVerticle(Class clazz) {
        VerticleRunner.runForJava(CORE_EXAMPLES_JAVA_DIR, clazz, true);
    }

    public static void runVertile(Class clazz) {
        VerticleRunner.runForJava(CORE_EXAMPLES_JAVA_DIR, clazz, false);
    }

}