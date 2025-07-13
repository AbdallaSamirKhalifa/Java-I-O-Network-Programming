package rmi.rmiclient;

import rmi.rmiinterfaces.*;
import java.rmi.*;
import java.rmi.registry.*;

public class Client {
    public static void main(String[] args) {

            Registry registry=getRegistry("localhost");
            if (registry==null )
                return;

            try {
                helloService(registry);
                calcService(registry);
                listingServices(registry);
                employeeDBService(registry);

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
            CalcService calcService=(CalcService) registry.lookup("Calc");

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
            HelloService helloService=(HelloService) registry.lookup("Hello");
            System.out.println(helloService.sendMessage("Remote message"));


    }
    public static void employeeDBService(Registry registry)throws RemoteException, NotBoundException{
        EmployeeService employeeService=(EmployeeService) registry.lookup("EmployeeDB");

        employeeService.add(new Employee(1,"Abdalla"));
        employeeService.add(new Employee(2,"Samir"));
        employeeService.add(new Employee(3,"Saad"));
        employeeService.add(new Employee(4,"Khalifa"));

        System.out.println("getByID: "+employeeService.getById(1));
        employeeService.update(new Employee(1,"Mohammed"));
        System.out.println("getById, after update: "+employeeService.getById(1));
        System.out.println("Listing Employees: ");
        for (Employee employee:employeeService.getAllEmployees())
            System.out.println(employee);

        employeeService.delete(new Employee(1,"Mohammed"));
        System.out.println("exists: "+employeeService.exists(1));
    }


}
