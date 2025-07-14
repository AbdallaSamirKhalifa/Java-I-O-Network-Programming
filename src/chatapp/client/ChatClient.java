package chatapp.client;

import chatapp.interfaces.ChatClientInt;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ChatClientInt {
    private final String name;
    private  ChatUI ui;
    public ChatClient(String name)throws RemoteException{
        this.name=name;
    }


    public void tellUser(String message)throws RemoteException{
        ui.writeMessage(message);
    }
    public String getName()throws RemoteException{
        return name;
    }
    public void setUi(ChatUI ui){
        this.ui=ui;
    }

}
