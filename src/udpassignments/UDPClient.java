package udpassignments;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {

    }
    public static void udpClient(){
        try (DatagramSocket dgSocket=new DatagramSocket()){

            try (Scanner scanner=new Scanner(new FileInputStream("src/udpassignments/testFile"),"UTF-8")){

                while (scanner.hasNextLine()){
                    String next=scanner.nextLine();
                    DatagramPacket dgRequest= new DatagramPacket
                            (next.getBytes(),next.length(), InetAddress.getLocalHost(),2222);
                    dgSocket.send(dgRequest);
                    dgSocket.setSoTimeout(200);
                }

            }catch (FileNotFoundException e){
                System.out.println("Error while searching for the file: "+e);
            }

        } catch (IOException e){
            System.out.println("Error on the sender socket: "+e);

        }
    }
}
