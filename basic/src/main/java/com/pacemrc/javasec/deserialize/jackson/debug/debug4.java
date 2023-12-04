package com.pacemrc.javasec.deserialize.jackson.debug;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * 演示反序列化
 */

class Person4 {
    protected String name;
    private String sex;
    static int age;
    final String className = "Info";
    public String flag = "flag{this_is_flag}";

    public Person4(){

    }
    public Person4(String name,String my_sex,int age){
        this.name = name;
        this.sex = my_sex;
        this.age = age;
    }
    public String getName(){
        System.out.println("getName has been used");
        return name;
    }
    public void setName(String name) {
        System.out.println("setName has been used");
        this.name = name;
    }
    public String getSex() {
        System.out.println("getSex has been used");
        return sex;
    }
    public void setSex(String sex) {
        System.out.println("sexSex has been used");
        this.sex = sex;
    }
    public static int getAge() {
        System.out.println("getAge has been used");
        return age;
    }
    public static void setAge(int age) {
        System.out.println("setAge has been used");
        Person4.age = age;
    }
    public String getClassName() {
        System.out.println("getClassName has been used");
        return className;
    }
    public String getFlag() {
        System.out.println("getFlag has been used");
        return flag;
    }
    public void setFlag(String flag) {
        System.out.println("setFlag has been used");
        this.flag = flag;
    }
}
public class debug4 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //序列化
        Person4 person4 = new Person4("caofalin","boy",24);
        String json = objectMapper.writeValueAsString(person4);
        //反序列化
        Person4 person = objectMapper.readValue(json,Person4.class);
        System.out.println(person);
    }

}
