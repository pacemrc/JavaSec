package com.pacemrc.javasec.classload;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class testPerson {

    public static void test1() throws ClassNotFoundException {
        System.out.println("---类加载---");
        Class.forName("com.pacemrc.javasec.classload.Person");
        //测试实例化对象时，执行哪些代码
        System.out.println("---实例化对象---");
        Person person = new Person("pacemec",23);
        //测试调用类的静态方法时，执行哪些代码
        System.out.println("---调用静态方法---");
        Person.staticAction();
        //测试给类的静态属性赋值，执行哪些代码
        System.out.println("---赋值静态属性---");
        Person.id = 1;
    }

    public static void test2() throws ClassNotFoundException {

        Class.forName("com.pacemrc.javasec.classload.Person");
        ClassLoader classLoader1 = ClassLoader.getSystemClassLoader();
        Class.forName("com.pacemrc.javasec.classload.Person",false,classLoader1);
    }

    public static void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
        //测试classloader.loadclass在加载类的时候是否会初始化
        Class<?> clz1 = classLoader.loadClass("com.pacemrc.javasec.classload.Person");
        //测试classloader.loadclass在实例化的时候是否会执行代码
        clz1.newInstance();

        //通过反射使得ClassLoader.loadClass加载类时进行连接阶段的解析操作
        ClassLoader classLoader2 = ClassLoader.getSystemClassLoader();
        Class<ClassLoader> classLoaderClass = ClassLoader.class;
        Method loadClass = classLoaderClass.getDeclaredMethod("loadClass", String.class, boolean.class);
        loadClass.setAccessible(true);
        Class clz2 = (Class) loadClass.invoke(classLoader2, "com.pacemrc.javasec.classload.Person", true);
        //开启解析操作的类实例化
        clz2.newInstance();
    }
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {

        //测试加载类时，执行哪些代码
//        test1();
        //测试Class.forName加载类
//        test2();
        //测试ClassLoader.loadclass加载类
        test3();

    }
}