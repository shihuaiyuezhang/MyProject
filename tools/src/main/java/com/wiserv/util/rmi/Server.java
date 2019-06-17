package com.wiserv.util.rmi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {

        try {
            SayHelloService service = new SayHelloServiceImpl();
            //创建注册表
            LocateRegistry.createRegistry(5678);
            //注册通讯路径
            Naming.rebind("rmi://127.0.0.1:5678/sayHelloService", service);
            System.out.print("server start");
            System.in.read();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
