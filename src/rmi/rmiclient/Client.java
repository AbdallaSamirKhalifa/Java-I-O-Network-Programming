package rmi.rmiclient;

import rmi.rmiinterfaces.ICalc;
import rmi.rmiinterfaces.IHelloService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry= LocateRegistry.getRegistry("localhost");
            try {
                IHelloService helloService=(IHelloService) registry.lookup("Hello");
                ICalc calcService=(ICalc) registry.lookup("Calc");

                System.out.println(helloService.sendMessage("Remote message"));
                System.out.println("Addition: "+calcService.add(10,20));
                System.out.println("Multiplication: "+calcService.multiply(10,10));

                System.out.println("--------------");
                System.out.println("Listing available services");
                for (String service:registry.list())
                    System.out.println(service);

            }catch (NotBoundException e){
            System.out.println("NotBound Exception on the client: "+e);
            }
        }catch (RemoteException e){
            System.out.println("Remote Exception on the client: "+e);
        }
    }
}
