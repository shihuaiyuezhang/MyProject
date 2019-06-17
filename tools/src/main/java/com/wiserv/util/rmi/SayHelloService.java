package com.wiserv.util.rmi;

import java.io.Serializable;
import java.rmi.Remote;

public interface SayHelloService extends Remote,Serializable{
    String sayHello(String name);
}
