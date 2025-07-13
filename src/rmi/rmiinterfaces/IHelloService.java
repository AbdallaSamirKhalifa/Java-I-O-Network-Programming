package rmi.rmiinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloService extends Remote {
    String sendMessage(String clientMessage)throws RemoteException;
}
