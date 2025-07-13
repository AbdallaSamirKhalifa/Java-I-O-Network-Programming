package rmi.rmiclient;

import rmi.rmiinterfaces.ICalc;
import rmi.rmiinterfaces.IHelloService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {

            Registry registry=getRegistry("localhost");
            if (registry==null )
                return;

            try {
                helloService(registry);
                calcService(registry);
                listingServices(registry);

            }catch (NotBoundException | RemoteException e){
            System.out.println("NotBound Exception on the client: "+e);
            }

    }
    public static Registry getRegistry(String host){
        try {
            return LocateRegistry.getRegistry(host);
        }catch (RemoteException e){
            System.out.println("Remote Exception on the client: "+e);

        }
        return null;
    }
    public static void calcService(Registry registry)throws RemoteException, NotBoundException{
            ICalc calcService=(ICalc) registry.lookup("Calc");

            System.out.println("Addition: "+calcService.add(10,20));
            System.out.println("Subtract: "+calcService.subtract(10,5));
            System.out.println("Multiplication: "+calcService.multiply(10,10));
            System.out.println("Divide: "+calcService.divide(2,1));


    }
    public static void listingServices(Registry registry)throws RemoteException{
        System.out.println("--------------");
        System.out.println("Listing available services");
        for (String service:registry.list())
            System.out.println(service);
    }
    public static void helloService(Registry registry)throws RemoteException, NotBoundException{
            IHelloService helloService=(IHelloService) registry.lookup("Hello");
            System.out.println(helloService.sendMessage("Remote message"));


    }
}
