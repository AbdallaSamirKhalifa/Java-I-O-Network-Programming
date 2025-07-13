package rmi.rmiinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface EmployeeService extends Remote {
    Employee getById(int id)throws RemoteException;
    void add(Employee employee)throws RemoteException;
    void update (Employee employee)throws RemoteException;
    void delete(Employee employee)throws RemoteException;
    boolean exists(int id)throws RemoteException;
    List<Employee>getAllEmployees()throws RemoteException;
}
