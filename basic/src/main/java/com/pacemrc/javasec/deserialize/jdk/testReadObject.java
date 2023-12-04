package com.pacemrc.javasec.deserialize.jdk;

import java.io.*;

public class testReadObject {

    public static byte[] testObjectOutputStream(User user) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(user);
        oos.close();
        bos.close();

        return bos.toByteArray();

    }

    public static void testObjectInputStream(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        System.out.println(object);
        ois.close();
        bis.close();
    }

    public static void main(String[] args) throws Exception {

        User user = new User();
        user.setId(2);
        user.setUsername("admin");
        user.setPassword("admin");
        System.out.println(user);
        System.out.println("start readObject...");
        byte[] bytes = testObjectOutputStream(user);
        testObjectInputStream(bytes);

    }
}
