package com.wiserv.util.rmi;

public class SayHelloServiceImpl implements SayHelloService {
    public String sayHello(String name) {
        return "hello," + name;
    }
}
