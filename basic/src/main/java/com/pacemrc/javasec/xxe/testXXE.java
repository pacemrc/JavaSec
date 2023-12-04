package com.pacemrc.javasec.xxe;

import org.apache.commons.digester3.Digester;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.*;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class testXXE {

    public static void xmlReader_1(String xmlFile) throws SAXException, IOException {

        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.parse(new InputSource(new StringReader(xmlFile)));

    }

    public static void xmlReader_2(String xmlFile) throws SAXException, IOException, ParserConfigurationException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.parse(new InputSource(new StringReader(xmlFile)));

    }

    public static void SAXBuilder_1(String xmlFile) throws IOException, JDOMException {

        SAXBuilder builder = new SAXBuilder();
        builder.build(new InputSource(new StringReader(xmlFile)));  // cause xxe
    }

    public static void SAXBuilder_2(String xmlFile) throws IOException, SAXException {

        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.parse(new InputSource(new StringReader(xmlFile)));  // parse xml
    }

    public static void SAXReader(String xmlFile) throws IOException, DocumentException {

        SAXReader reader = new SAXReader();
        // org.dom4j.Document document
        reader.read(new InputSource(new StringReader(xmlFile))); // cause xxe


    }

    public static void SAXParser(String xmlFile) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser parser = spf.newSAXParser();
        parser.parse(new InputSource(new StringReader(xmlFile)), new DefaultHandler());  // parse xml

    }

    public static void Digester(String xmlFile) throws IOException, SAXException {
        Digester digester = new Digester();
        digester.parse(new StringReader(xmlFile));  // parse xml
    }

    public static void DocumentBuilder(String xmlFile) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(xmlFile);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);  // parse xml
        //用于命令回显
        // 遍历xml节点name和value
        StringBuilder buf = new StringBuilder();
        NodeList rootNodeList = document.getChildNodes();
        for (int i = 0; i < rootNodeList.getLength(); i++) {
            Node rootNode = rootNodeList.item(i);
            NodeList child = rootNode.getChildNodes();
            for (int j = 0; j < child.getLength(); j++) {
                Node node = child.item(j);
                buf.append(String.format("%s: %s\n", node.getNodeName(), node.getTextContent()));
            }
        }
        sr.close();
        System.out.println(buf.toString());
    }

    public static void DocumentHelper(String xmlFile) throws DocumentException {
        DocumentHelper.parseText(xmlFile);

    }
    public static void main(String[] args) throws IOException, SAXException, JDOMException, DocumentException, ParserConfigurationException {
        String xmlFilePath = System.getProperty("user.dir")+ "\\src\\main\\resources\\xmlFile\\xxe.xml";
        String xmlFile = new String(Files.readAllBytes(Paths.get(xmlFilePath)));
        //执行结果无回显，使用dnslog测试
        xmlReader_1(xmlFile);
//        xmlReader_2(xmlFile);
//        SAXBuilder_1(xmlFile);
//        SAXBuilder_2(xmlFile);
//        SAXReader(xmlFile);
//        SAXParser(xmlFile);
//        Digester(xmlFile);
//        DocumentHelper(xmlFile);
//        读取文件内容可回显
//        DocumentBuilder(xmlFile);


    }
}
