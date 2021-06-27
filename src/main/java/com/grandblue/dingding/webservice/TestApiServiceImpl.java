package com.grandblue.dingding.webservice;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Component
@WebService(name = "testApiService",
        targetNamespace = "http://andot.org/webservice/demo/server",
        endpointInterface = "com.grandblue.dingding.webservice.TestApiService",
        portName = "10000")
public class TestApiServiceImpl implements TestApiService {
    @Override
    public String insertPersonInfo() {
        String a ="你好";
        return a;
    }
}
