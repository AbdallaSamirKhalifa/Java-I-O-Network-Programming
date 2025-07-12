package udpassignments;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {


    }
    public static void udpServer(){
        try (DatagramSocket dgSocket=new DatagramSocket(2222)){
            byte[]buffer=new byte[1000];
            while (true){
                DatagramPacket dgRequest=new DatagramPacket(buffer,buffer.length);
                dgSocket.receive(dgRequest);
                System.out.println(new String(dgRequest.getData()).trim()+" Port: "+dgRequest.getPort());
            }
        }catch (IOException e){
            System.out.println("An error occurred while receiving the data: "+e);
        }
    }
}
