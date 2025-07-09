package broadcastChatApp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void broadCastChatApp(){

        try (ServerSocket serverSocket=new ServerSocket(7777)){
            while (true){
                Socket socket=serverSocket.accept();
                Runnable runnable=new ThreadedEchoHandler(socket);
                Thread thread=new Thread(runnable);
                thread.start();
            }
        }catch (IOException e){
            System.out.println("Exception on the server socket: "+e);
        }

    }
}


