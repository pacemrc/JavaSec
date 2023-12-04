package com.pacemrc.javasec.deserialize.jackson.debug;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 测试私有、保护、静态属性的序列化
 */

class Person2{
    protected String name;
    private String sex;
    static int age;
    final String className = "debug.Person";
    public String flag = "flag{this is flag}";

    public Person2(String name,String sex,int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

}

public class debug2 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        Person2 person2 = new Person2("caofalin", "man", 24);
        String string = objectMapper.writeValueAsString(person2);
        System.out.println(string);
    }
}
