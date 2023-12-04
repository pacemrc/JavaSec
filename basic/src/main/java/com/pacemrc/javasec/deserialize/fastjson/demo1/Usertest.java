package com.pacemrc.javasec.deserialize.fastjson.demo1;

import com.alibaba.fastjson.JSON;
/**
 * 演示三种反序列化方式返回的类不同
 */
class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
public class Usertest {

    public static void main(String[] args) {
        //创建一个用于实验的user类
        User user1 = new User();
        user1.setName("caofalin");
        user1.setAge(23);

        //序列化
        String serializedStr = JSON.toJSONString(user1);
        System.out.println("serializedStr="+serializedStr);

        //通过parse方法进行反序列化，返回的是一个JSONObject
        Object obj1 = JSON.parse(serializedStr);
        System.out.println("parse反序列化对象名称:"+obj1.getClass().getName());
        System.out.println("parse反序列化："+obj1);

        //通过parseObject,不指定类，返回的是一个JSONObject
        Object obj2 = JSON.parseObject(serializedStr);
        System.out.println("parseObject反序列化对象名称:"+obj2.getClass().getName());
        System.out.println("parseObject反序列化:"+obj2);

        //通过parseObject,指定类后返回的是一个相应的类对象
        Object obj3 = JSON.parseObject(serializedStr,User.class);
        System.out.println("parseObject反序列化对象名称:"+obj3.getClass().getName());
        System.out.println("parseObject反序列化:"+obj3);
    }
}