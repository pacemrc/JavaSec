package com.pacemrc.javasec.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteObj extends Remote {
    //sayHello就是客户端要调用的方法，需要抛出RemoteException
    public void getCalc() throws IOException;
}
