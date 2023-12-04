package com.pacemrc.javasec.jndi;

import javax.naming.InitialContext;

public class JNDILDAPClient {
    public static void main(String[] args) throws Exception {

        InitialContext initialContext = new InitialContext();
        initialContext.lookup("ldap://127.0.0.1:10389/cn=test,dc=example,dc=com");


    }
}
