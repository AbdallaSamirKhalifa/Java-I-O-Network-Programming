package chatapp.server;

import chatapp.interfaces.*;

import javax.swing.*;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Vector;

public class ChatServer extends UnicastRemoteObject implements ChatServerInt {
    private final Vector<ChatClientInt>loggedIn=new Vector<>();
    public ChatServer()throws RemoteException{}

    @Override
    public void login(ChatClientInt client)throws RemoteException{
        logToFile(client.getName()+" Connected at "+LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss"))+"\n");
        client.tellUser("You have connected successfully.");
        publishToClients(client.getName()+" Has Just Connected.");
        loggedIn.add(client);
    }

    @Override
    public void publishToClients(String message)throws RemoteException{
        for (ChatClientInt client: loggedIn)
            client.tellUser(message);
    }

    @Override
    public void logout(ChatClientInt client)throws RemoteException{
        logToFile(client.getName()+" Disconnected at "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss"))+"\n");
        client.tellUser("You Have Disconnected.");
        publishToClients(client.getName()+ " Just Disconnected.");
        loggedIn.remove(client);
    }

    @Override
    public Vector<ChatClientInt>getConnected()throws RemoteException{
        return loggedIn;
    }

    private void logToFile(String message){
        try (FileWriter writer=new FileWriter ("src/chatapp/server/logFile.text", true)){
            writer.write(message);
        }catch (IOException e){
            System.out.println("Error on server logger: "+e);
        }
    }


}
