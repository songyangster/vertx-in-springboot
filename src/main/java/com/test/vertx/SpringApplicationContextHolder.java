package com.test.vertx;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

class SpringApplicationContextHolder {
    private static ApplicationContext appContext;

    public static void autowireBean(Object bean, String beanName) {
        AutowireCapableBeanFactory factory = appContext.getAutowireCapableBeanFactory();
        factory.autowireBean( bean );
        factory.initializeBean( bean, beanName );
    }

    public static void setAppContext(ApplicationContext appContext) {
        if (appContext != null) SpringApplicationContextHolder.appContext = appContext;
    }
}
