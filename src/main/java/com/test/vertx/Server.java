package com.test.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.apex.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.vertx.ext.apex.Router.router;

@Component
public class Server extends AbstractVerticle {

    @Autowired
    DummyService dummyService;

    @Override
    public void start() throws Exception {
        SpringApplicationContextHolder.autowireBean(this, "serverVerticle");

        HttpServer server = vertx.createHttpServer();
        Router router = router(vertx);
        server.requestHandler(router::accept).listen(4080);

        // This handler will be called for every request
        router.route("/").handler(routingContext -> {
            System.out.println("Thread = " + Thread.currentThread().getName());

            assert(dummyService != null);
            dummyService.dummyMethod();

            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.setChunked(true);
            response.write("Hello Vert.x World");
            response.end();
        });
    }
}