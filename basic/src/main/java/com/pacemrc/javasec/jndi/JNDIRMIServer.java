package com.pacemrc.javasec.jndi;

import javax.naming.InitialContext;
import javax.naming.Reference;

/**
 * 使用需要先开启RMI服务
 */
public class JNDIRMIServer {
    public static void main(String[] args) throws Exception {
        InitialContext initialContext = new InitialContext();
        //绑定远程对象
//        initialContext.rebind("rmi://127.0.0.1:1099/remoteObj", new RemoteObjImpl());
        //绑定引用对象
        Reference refObj = new Reference("org.demo.TestRef", "org.demo.TestRef", "http://localhost:8000/");
        initialContext.rebind("rmi://127.0.0.1:1099/remoteObj", refObj);
    }
}
