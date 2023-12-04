package com.pacemrc.javasec.rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObjImpl extends UnicastRemoteObject implements IRemoteObj {

    public RemoteObjImpl() throws RemoteException {
        //UnicastRemoteObject.exportOnject(this, 0);//如果不继承UnicastRemoteObject就需要手工导出
    }

    @Override
    public void getCalc() throws IOException {
        Runtime.getRuntime().exec("calc");
    }
}


