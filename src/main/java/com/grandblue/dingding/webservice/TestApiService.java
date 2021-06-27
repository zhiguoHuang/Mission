package com.grandblue.dingding.webservice;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://andot.org/webservice/demo/server")
public interface TestApiService {
    @WebMethod
    String insertPersonInfo();
}
