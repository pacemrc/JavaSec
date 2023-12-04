package com.pacemrc.javasec.deserialize.fastjson.demo1;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Properties;

/**
 * 演示用@type字段指定类的方法，是否会自动调用类中特定的setter、getter方法
 */



class Person {
    //属性
    public String name;
    private String full_name;
    private int age;
    private Boolean sex;
    private Properties prop;
    //构造函数
    public Person(){
        System.out.println("Person构造函数");
    }
    //set
    public void setAge(int age) throws IOException {
        System.out.println("setAge()");
        Runtime.getRuntime().exec("calc");
        this.age = age;
    }
    //get 返回Boolean
    public Boolean getSex(){
        System.out.println("getSex()");
        return this.sex;
    }
    //get 返回ProPerties
    public Properties getProp(){
        System.out.println("getProp()");
        return this.prop;
    }
    //在输出时会自动调用的对象ToString函数
    public String toString() {
        String s = "[Person Object] name=" + this.name + " full_name=" + this.full_name  + ", age=" + this.age + ", prop=" + this.prop + ", sex=" + this.sex;
        return s;
    }

}
public class Persontest {

    public static void main(String[] args) {
//        String eneity3 = "{\"@type\":\"Person\", \"name\":\"falin\", \"full_name\":\"caofalin\", \"age\": 23, \"prop\": {\"123\":123}, \"sex\": 1}";
        String eneity3 = "{\"age\": 23, \"prop\": {\"123\":123}, \"sex\": 1}";

        //反序列化
        Object obj = JSON.parseObject(eneity3,Person.class);
        //输出会调用obj对象的tooString函数
        System.out.println(obj);
    }
}