package com.pacemrc.javasec.rmi;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) throws NotBoundException, IOException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
        IRemoteObj remoteObj = (IRemoteObj) registry.lookup("remoteObj");
        remoteObj.getCalc();
    }
}
