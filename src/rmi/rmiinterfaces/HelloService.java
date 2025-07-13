package rmi.rmiinterfaces;

import java.rmi.*;

public interface HelloService extends Remote {
    String sendMessage(String clientMessage)throws RemoteException;
}
