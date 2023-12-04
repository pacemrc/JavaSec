package com.pacemrc.javasec.utils;

import java.io.*;

public class Deserialize {

    public static byte[] serialize(Object obj) throws IOException {

        System.out.println("call---serialize()");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
        bos.close();

        return bos.toByteArray();
    }

    public static void deserialize(byte[] bytes) throws Exception{

        System.out.println("call---deserialize()");
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object o = ois.readObject();
        ois.close();
        bis.close();

    }

    public static void doSerialAndDeserial(Object obj) throws Exception {

        try {
            byte[] serialize = serialize(obj);
            deserialize(serialize);
        }
        catch (Exception ignored) {
        }
    }

}
