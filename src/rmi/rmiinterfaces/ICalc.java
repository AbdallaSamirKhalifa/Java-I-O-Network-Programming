package rmi.rmiinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalc extends Remote {
    int add(int num1, int num2) throws RemoteException;
    int multiply(int num1, int num2) throws RemoteException;
}
