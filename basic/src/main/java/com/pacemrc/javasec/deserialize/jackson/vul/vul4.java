package com.pacemrc.javasec.deserialize.jackson.vul;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
class Person {
    protected String name;
    private String sex;
    static int age;
    final String className = "Person";
    FlagHandler flag = new Flag();
    public Person(){
    }
    public Person(String name,String my_sex,int age){
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
        Person.age = age;
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
    public String getFlagName(){
        System.out.println("getFlagName has been used");
        return this.flagName;
    }
    public void setFlagName(String flagName){
        System.out.println("setFlagName has been used");
        this.flagName = flagName;
    }
    public String getFlagValue() {
        System.out.println("getFlagValue has been used");
        return flagValue;
    }
    public void setFlagValue(String flagValue) {
        System.out.println("setFlagValue has been used");
        this.flagValue = flagValue;
    }
}
interface FlagHandler{

}
public class vul4{
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        // serialize
        Person Person = new Person("caofalin","boy",24);
        String json = objectMapper.writeValueAsString(Person);
        System.out.println(json);
        // deserialize
        Person readPerson = objectMapper.readValue(json,Person.class);
        System.out.println(readPerson);
    }
}