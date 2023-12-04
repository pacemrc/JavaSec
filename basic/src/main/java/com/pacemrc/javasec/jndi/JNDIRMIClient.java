package com.pacemrc.javasec.jndi;

import com.pacemrc.javasec.rmi.IRemoteObj;

import javax.naming.InitialContext;

public class JNDIRMIClient {
    public static void main(String[] args) throws Exception {

        InitialContext initialContext = new InitialContext();
        IRemoteObj remoteObj = (IRemoteObj)initialContext.lookup("rmi://127.0.0.1:1099/remoteObj");
        remoteObj.getCalc();

    }
}
