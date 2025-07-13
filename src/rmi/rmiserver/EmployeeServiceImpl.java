package rmi.rmiserver;

import rmi.rmiinterfaces.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class EmployeeServiceImpl extends UnicastRemoteObject implements EmployeeService {

    private Map<Integer,Employee>employeeDB;
    public EmployeeServiceImpl()throws RemoteException{
        employeeDB=new HashMap<>();

    }
    public void add(Employee employee){
        employeeDB.put(employee.getId(),employee);
    }

    public void update(Employee employee){
        if (employeeDB.containsKey(employee.getId()))
            employeeDB.replace(employee.getId(),employee);

    }
    public Employee getById(int id){
            return employeeDB.getOrDefault(id,null);
    }
    public void delete(Employee employee){
        employeeDB.remove(employee.getId());

    }
    public boolean exists(int id){
        return employeeDB.containsKey(id);
    }

    public List<Employee>getAllEmployees(){
        return new ArrayList<>(employeeDB.values());
    }
}
