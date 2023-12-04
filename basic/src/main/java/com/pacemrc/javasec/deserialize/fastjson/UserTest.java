package com.pacemrc.javasec.deserialize.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.Properties;

class User {
    public String name1; //public且有get set
    public String name2; //public且有get
    public String name3; //public且有set
    public String name4; //仅仅public
    private String age1; //private且有get set
    private String age2; //private且有get
    private String age3; //private且有set
    private String age4; //仅仅private
    public Properties prop1_1; //public且有get set
    public Properties prop1_2; //public且有get
    public Properties prop1_3; //public且有set
    public Properties prop1_4; //仅仅public
    private Properties prop2_1; //private且有get set
    private Properties prop2_2; //private且有get
    private Properties prop2_3; //private且有set
    private Properties prop2_4; //仅仅private
    @Override
    public String toString() {
        return "User{" +
                "name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                ", name3='" + name3 + '\'' +
                ", name4='" + name4 + '\'' +
                ", age1='" + age1 + '\'' +
                ", age2='" + age2 + '\'' +
                ", age3='" + age3 + '\'' +
                ", age4='" + age4 + '\'' +
                ", prop1_1=" + prop1_1 +
                ", prop1_2=" + prop1_2 +
                ", prop1_3=" + prop1_3 +
                ", prop1_4=" + prop1_4 +
                ", prop2_1=" + prop2_1 +
                ", prop2_2=" + prop2_2 +
                ", prop2_3=" + prop2_3 +
                ", prop2_4=" + prop2_4 +
                '}';
    }
    public void setProp1_3(Properties prop1_3) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.prop1_3 = prop1_3;
    }
    public void setProp2_3(Properties prop2_3) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.prop2_3 = prop2_3;
    }
    public Properties getProp1_2() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return prop1_2;
    }
    public Properties getProp2_2() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return prop2_2;
    }
    public Properties getProp1_1() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return prop1_1;
    }
    public void setProp1_1(Properties prop1_1) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.prop1_1 = prop1_1;
    }
    public Properties getProp2_1() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return prop2_1;
    }
    public void setProp2_1(Properties prop2_1) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.prop2_1 = prop2_1;
    }
    public String getName1() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return name1;
    }
    public void setName1(String name1) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.name1 = name1;
    }
    public String getAge1() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return age1;
    }
    public void setAge1(String age1) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.age1 = age1;
    }
    public String getName2() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return name2;
    }
    public String getAge2() {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        return age2;
    }
    public void setName3(String name3) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.name3 = name3;
    }
    public void setAge3(String age3) {
        String methodName = Thread.currentThread().getStackTrace()
                [1].getMethodName();
        System.out.println(methodName + "() is called");
        this.age3 = age3;
    }
    public User() {
        this.name1 = "rai4over1";
        this.name2 = "rai4over2";
        this.name3 = "rai4over3";
        this.name4 = "rai4over4";
        this.age1 = "a1";
        this.age2 = "a2";
        this.age3 = "a3";
        this.age4 = "a4";
        prop1_1 = new Properties();
        prop1_2 = new Properties();
        prop1_3 = new Properties();
        prop1_4 = new Properties();
        prop1_1.put("prop1_1", "1_1");
        prop1_2.put("prop1_2", "1_2");
        prop1_3.put("prop1_3", "1_3");
        prop1_4.put("prop1_4", "1_4");
        prop2_1 = new Properties();
        prop2_2 = new Properties();
        prop2_3 = new Properties();
        prop2_4 = new Properties();
        prop2_1.put("prop2_1", "2_1");
        prop2_2.put("prop2_2", "2_2");
        prop2_3.put("prop2_3", "2_3");
        prop2_4.put("prop2_4", "2_4");
        System.out.println("User init() is called");
    }
}
public class UserTest {
    public static void main(String[] args) {
        /**
         * 序列化
         */
        System.out.println("========序列化========");
        //非自省（序列化的类没有类名信息）
        User a = new User();
        System.out.println("===========================");
        String jsonstr_a = JSON.toJSONString(a);
        System.out.println(jsonstr_a);

        //自省（序列化的类有类名信息）
//        User a = new User();
//        System.out.println("===========================");
//        String jsonstr_a = JSON.toJSONString(a, SerializerFeature.WriteClassName);
//        System.out.println(jsonstr_a);

        /**
         * 反序列化
         */
        System.out.println("========反序列化========");
        //非自省（没有指定反序列化的类）
//        String jsonstr_a = " {\"age1\":\"a1\",\"age2\":\"a2\",\"age3\":\"a3\",\"age4\":\"a4\",\"name1\":\"rai4over1\",\"name2\":\"rai4over2\",\"name3\":\"rai4over3\",\"name4\":\"rai4ove r4\",\"prop1_1\":{\"prop1_1\":\"1_1\"},\"prop1_2\": {\"prop1_2\":\"1_2\"},\"prop1_3\":{\"prop1_3\":\"1_3\"},\"prop1_4\": {\"prop1_4\":\"1_4\"},\"prop2_1\":{\"prop2_1\":\"2_1\"},\"prop2_2\": {\"prop2_2\":\"2_2\"},\"prop2_3\":{\"prop2_3\":\"2_3\"},\"prop2_4\": {\"prop2_4\":\"2_4\"}}";
//        System.out.println(jsonstr_a);
//        System.out.println("===========================");
//        User b = JSON.parseObject(jsonstr_a, User.class);
//        System.out.println(b);

        //自省（指定反序列化的类）
//        String jsonstr_a = "{\"@type\":\"User\",\"age1\":\"a1\",\"age2\":\"a2\",\"name1\":\"rai4over1\",\"name2\":\"rai4over2\",\"name3\":\"rai4over3\",\"name4\":\"rai4over4\",\"prop1_1\":{\"@type\":\"java.util.Properties\",\"prop1_1\":\"1_1\"},\"prop1_2\":{\"@type\":\"java.util.Properties\",\"prop1_2\":\"1_2\"},\"prop1_3\":{\"@type\":\"java.util.Properties\",\"prop1_3\":\"1_3\"},\"prop1_4\":{\"@type\":\"java.util.Properties\",\"prop1_4\":\"1_4\"},\"prop2_1\":{\"@type\":\"java.util.Properties\",\"prop2_1\":\"2_1\"},\"prop2_2\":{\"@type\":\"java.util.Properties\",\"prop2_2\":\"2_2\"}}\n";
//        System.out.println(jsonstr_a);
//        System.out.println("===========================");
        /**
         * parseObject(String text, Class<T> clazz) ，构造⽅法 + setter + 满⾜条件额外的getter
         */
        User b = JSON.parseObject(jsonstr_a, User.class);
        /**
         * JSONObject parseObject(String text) ，构造⽅法 + setter + getter + 满⾜条件额外的 getter
         */
//        JSONObject b = JSON.parseObject(jsonstr_a);
        /**
         * parse(String text) ，构造⽅法 + setter + 满⾜条件额外的 getter
         */
//        Object b = JSON.parse(jsonstr_a);
        System.out.println(b);
    }
}