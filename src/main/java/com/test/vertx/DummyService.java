package com.test.vertx;

import org.springframework.stereotype.Service;

@Service
public class DummyService {

    public static DummyService dummyService;

    public DummyService() {
        dummyService = this;
    }

    public String dummyMethod(){
        return "Dummy String";
    }
}
