package com.pacemrc.javasec.deserialize.jackson.debug;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 测试私有、保护、静态属性含有setter、getter方法的序列化过程
 */

class Person3{
    protected String name;
    private String sex;
    static int age;
    final String className = "debug.Person";
    public String flag = "flag{this is flag}";

    public Person3(String name,String sex,int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        System.out.println("getName has been called");
        return name;
    }

    public void setName(String name) {
        System.out.println("setName has been called");
        this.name = name;
    }

    public String getSex() {
        System.out.println("getSex has been called");
        return sex;
    }

    public void setSex(String sex) {
        System.out.println("setSex has been called");
        this.sex = sex;
    }

    public static int getAge() {
        System.out.println("getAge has been called");
        return age;
    }

    public static void setAge(int age) {
        System.out.println("setAge has been called");
        Person2.age = age;
    }

    public String getClassName() {
        System.out.println("getClassName has been called");
        return className;
    }

    public String getFlag() {
        System.out.println("getFlag has been called");
        return flag;
    }

    public void setFlag(String flag) {
        System.out.println("setFlag has been called");
        this.flag = flag;
    }
}

public class debug3 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person3 person3 = new Person3("caofalin", "man", 24);
        String string = objectMapper.writeValueAsString(person3);
        System.out.println(string);
    }
}
