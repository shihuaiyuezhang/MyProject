package com.wiserv.util.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        SayHelloService service = null;
        try {
            service = (SayHelloService) Naming.lookup("rmi://127.0.0.1:5678/sayHelloService");
            System.out.println(service.sayHello("Mr.bin"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
