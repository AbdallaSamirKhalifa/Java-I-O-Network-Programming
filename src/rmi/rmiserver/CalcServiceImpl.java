package rmi.rmiserver;

import rmi.rmiinterfaces.ICalc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcServiceImpl extends UnicastRemoteObject implements ICalc {
    public CalcServiceImpl()throws RemoteException{}
    public int add(int num1,int num2){
        return num1+num2;
    }

    public int multiply(int num1,int num2){
        return num1*num2;
    }

    public int subtract(int num1,int num2){
        return num1-num2;
    }

    public int divide(int num1,int num2){
        if (num2==0)
            return 0;

        return num1/num2;
    }
}
