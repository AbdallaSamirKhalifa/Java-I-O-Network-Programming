package rmi.rmiserver;

import rmi.rmiinterfaces.*;
import java.rmi.RemoteException;
import java.rmi.registry.*;

public class Server {
    public static void main(String[] args) {
        try {
            HelloService helloService=new HelloServiceImpl();
            CalcService calcService=new CalcServiceImpl();
            EmployeeService employeeService=new EmployeeServiceImpl();
            Registry registry= LocateRegistry.createRegistry(1099);
            registry.rebind("Hello",helloService);
            registry.rebind("Calc",calcService);
            registry.rebind("EmployeeDB",employeeService);
        }catch (RemoteException r){
            System.out.println("Remote exception on the server: "+r);
        }
    }
}
