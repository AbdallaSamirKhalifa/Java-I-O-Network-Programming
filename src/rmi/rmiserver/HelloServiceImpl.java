package rmi.rmiserver;

import rmi.rmiinterfaces.IHelloService;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements IHelloService {
    public HelloServiceImpl()throws RemoteException{}
    public String sendMessage(String clientMessage){
        return "Hello: "+ clientMessage;
    }
}
