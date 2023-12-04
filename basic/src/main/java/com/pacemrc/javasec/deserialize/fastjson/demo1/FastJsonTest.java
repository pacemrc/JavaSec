package com.pacemrc.javasec.deserialize.fastjson.demo1;

import com.alibaba.fastjson.JSON;
import java.io.IOException;

/**
 * 演示三种反序列化方法在执行过程中调用的方法不同
 */
public class FastJsonTest {

    public String name;
    public String age;
    public FastJsonTest() throws IOException {
    }

    public void setName(String test) {
        System.out.println("name setter called");
        this.name = test;
    }

    public String getName() {
        System.out.println("name getter called");
        return this.name;
    }

    public String getAge(){
        System.out.println("age getter called");
        return this.age;
    }

    public static void main(String[] args) {
        System.out.println("//1111.JSON.parse(\"\")");
        Object obj = JSON.parse("{\"@type\":\"FastJsonTest\",\"name\":\"thisisname\", \"age\":\"thisisage\"}");
        System.out.println(obj);
        System.out.println("//2222.JSON.parseObject(\"\")");
        Object obj2 = JSON.parseObject("{\"@type\":\"FastJsonTest\",\"name\":\"thisisname\", \"age\":\"thisisage\"}");
        System.out.println(obj2);
        System.out.println("//3333.JSON.parseObject(\"\",class)");
        Object obj3 = JSON.parseObject("{\"@type\":\"FastJsonTest\",\"name\":\"thisisname\", \"age\":\"thisisage\"}",FastJsonTest.class);
        System.out.println(obj3);
    }
}