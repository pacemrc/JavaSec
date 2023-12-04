package com.pacemrc.javasec.deserialize.jackson.debug;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 演示Jackson对Java多态的⽀持,enableDefaultTyping()
 * 在实际情况中类的属性往往不会这么简单，通常某个属性都是另外⼀个类的实例
 */

class Person5{
    protected String name;
    private String sex;
    static int age;
    final String className = "debug.Person5";
    FlagHandler flag = new Flag();

    public Person5(){

    }
    public Person5(String name,String my_sex,int age){
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
        Person5.age = age;
    }
    public String getClassName() {
        System.out.println("getClassName has been used");
        return className;
    }
    public FlagHandler getFlag() {
        System.out.println("getFlag has been used");
        return flag;
    }
    public void setFlag(FlagHandler flag) {
        System.out.println("setFlag has been used");
        this.flag = flag;
    }
}
class Flag implements FlagHandler{
    public String flagName;
    public String flagValue;

    public Flag(){
        this.flagName = "d0g3_flag";
        this.flagValue = "here_is_d0g3";
    }
}
interface FlagHandler{

}

public class debug5 {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        //序列化
        Person5 person5 = new Person5("caofalin","boy",24);
        String json = objectMapper.writeValueAsString(person5);
        System.out.println(json);
        //反序列化
        Person5 person = objectMapper.readValue(json,Person5.class);
        System.out.println(person);
    }
}
