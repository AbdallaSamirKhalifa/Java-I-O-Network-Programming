package rmi.rmiserver;

import rmi.rmiinterfaces.ICalc;
import rmi.rmiinterfaces.IHelloService;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            IHelloService helloService=new HelloServiceImpl();
            ICalc calcService=new CalcServiceImpl();
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind("Hello",helloService);
            registry.rebind("Calc",calcService);
        }catch (RemoteException r){
            System.out.println("Remote exception on the server: "+r);
        }
    }
}
