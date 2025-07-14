package chatapp.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface ChatServerInt extends Remote {
    void login(ChatClientInt client)throws RemoteException;
    void logout(ChatClientInt client)throws RemoteException;
    void publishToClients(String message)throws RemoteException;
    Vector<ChatClientInt>getConnected()throws RemoteException;
}
