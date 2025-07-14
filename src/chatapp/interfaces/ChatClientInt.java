package chatapp.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInt extends Remote {
    void tellUser(String message)throws RemoteException;
    String getName()throws RemoteException;

}
