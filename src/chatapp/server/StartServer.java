package chatapp.server;

import jdk.nashorn.api.tree.TryTree;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;

public class StartServer {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ChatServer server=new ChatServer();
            Naming.rebind("Chat",server);
//            Naming.rebind("rmi://127.0.0.1/chat",server);
            System.out.println("Chat server is ready.");
        }catch (RemoteException | MalformedURLException e){
            System.out.println("Chat server failed to start "+e);
        }
    }

}
