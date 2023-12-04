package com.pacemrc.javasec.deserialize.jackson.debug;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 测试公开属性的序列化
 */
class Person{
    public String name;
    public int age;
}

public class debug1 {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person();
        String string = objectMapper.writeValueAsString(person);
        System.out.println(string);
    }
}
