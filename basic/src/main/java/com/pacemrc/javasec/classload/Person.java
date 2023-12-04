package com.pacemrc.javasec.classload;

import java.io.Serializable;

public class Person implements Serializable {

    public String name;
    private int age;

    public static int id;
    static {
        System.out.println("执行了静态代码块");
    }
    public static void staticAction(){
        System.out.println("执行了静态方法");
    }

    {
        System.out.println("执行了构造代码块");
    }
    public Person() {
        System.out.println("执行了无参构造函数");
    }
    public Person(String name, int age) {
        System.out.println("执行了有参构造函数");
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private void action(String act) {
        System.out.println(act);
    }
}



