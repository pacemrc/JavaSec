package com.pacemrc.javasec.deserialize.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class testXMLDecoder {

    public static void testXMLDecoder(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream data = new BufferedInputStream(fis);
        XMLDecoder xmlDecoder = new XMLDecoder(data);
        xmlDecoder.readObject();

    }

    public static void testXMLEncoder(String path,Object obj) throws FileNotFoundException {

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
        xmlEncoder.writeObject(obj);
        xmlEncoder.close();

    }

    public static void main(String[] args) throws Exception {
        String path1 = "src/main/resources/xmlFile/test.xml";
        String path2 = "src/main/resources/xmlFile/calc.xml";

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");

        testXMLEncoder(path1,arrayList);
        testXMLDecoder(path2);



    }
}
