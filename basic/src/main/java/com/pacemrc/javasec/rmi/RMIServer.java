package com.pacemrc.javasec.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * REFERENCE
 * https://www.bilibili.com/video/BV1L3411a7ax?spm_id_from=333.999.0.0&vd_source=12fd51019b9ffc7fda3230e04dc3b344
 */
public class RMIServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        IRemoteObj remoteObj = new RemoteObjImpl();
        Registry r = LocateRegistry.createRegistry(1099);
        r.bind("remoteObj",remoteObj);
    }
}
