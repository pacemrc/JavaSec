package com.pacemrc.javasec.classload;

import com.pacemrc.javasec.deserialize.ysoserial.Calc;
import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import java.lang.reflect.Field;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import sun.misc.Unsafe;


/**
 * 学习地址：
 * 视频：https://www.bilibili.com/video/BV16h411z7o9?p=4&vd_source=12fd51019b9ffc7fda3230e04dc3b344
 * 文章：https://segmentfault.com/a/1190000023876273、https://juejin.cn/post/6844903838927814669
 *
 * 类继承关系：
 * ClassLoader (java.lang)
 *     SecureClassLoader (java.security)
 *         URLClassLoader (java.net)
 *             AppClassLoader in Launcher (sun.misc)
 */
public class classloadDemo {
    public static void testURLClassLoader() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {

        //URLClassLoader任意类加载：file/http/jar协议
        String url1 = "http://localhost:9999/";
        String url2 = "file:///"+System.getProperty("user.dir")+"\\src\\main\\resources\\classFile\\";
        String url3 = "jar:http://localhost:9999/!/";
        String url4 = "jar:file:///"+System.getProperty("user.dir")+"\\src\\main\\resources\\classFile\\!/";
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(url1)});
        Class<?> loadClass = urlClassLoader.loadClass("Calc");
        loadClass.newInstance();
    }

    public static void testClassLoader_defineClass() throws NoSuchMethodException, IOException, InvocationTargetException, IllegalAccessException, InstantiationException {

        //ClassLoader.defineClass 字节码加载任意类 私有
        ClassLoader  classLoader = ClassLoader .getSystemClassLoader();
        Method defineClassMethod = ClassLoader.class.getDeclaredMethod("defineClass",String.class, byte[].class, int.class, int.class);
        defineClassMethod.setAccessible(true);
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/classFile/Calc.class"));
        Class c = (Class) defineClassMethod.invoke(classLoader, "Calc", bytes, 0, bytes.length);
        c.newInstance();
    }

    public static void testUnsafe_defineClass() throws NoSuchFieldException, IllegalAccessException, IOException, InstantiationException {

        //Unsafe.defineClass字节码加载任意类 public类不能直接生成
        Class c = Unsafe.class;
        Field theUnsafeField = c.getDeclaredField("theUnsafe");
        theUnsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafeField.get(null);
        ClassLoader  classLoader = ClassLoader .getSystemClassLoader();
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/classFile/Calc.class"));
        Class<?> calc = unsafe.defineClass("Calc", bytes, 0,bytes.length, classLoader,null);
        calc.newInstance();

    }

    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static void testTemplatesImpl() throws Exception {

        JavaClass javaClass = Repository.lookupClass(Calc.class);
        byte[] code = javaClass.getBytes();
        TemplatesImpl obj = new TemplatesImpl();
        setFieldValue(obj, "_bytecodes", new byte[][] {code});
        setFieldValue(obj, "_name", "whatever");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());

        obj.newTransformer();
    }

    public static void testBCEL() throws Exception{

        JavaClass cls = Repository.lookupClass(Calc.class);
        String code = Utility.encode(cls.getBytes(), true);
        String bcel_code = "$$BCEL$$" + code;
        Class<?> loadClass = new com.sun.org.apache.bcel.internal.util.ClassLoader().loadClass(bcel_code);
        loadClass.newInstance();

    }

    public static void main(String[] args) throws Exception {

        //URLClassLoader加载任意对象
//        testURLClassLoader();

        //通过ClassLoader的defineClass加载字节码
//        testClassLoader_defineClass();

        //通过unsafe的defineClass加载字节码
        testUnsafe_defineClass();

        //通过TemplatesImpl加载类
//        testTemplatesImpl();

        //通过BCEL加载类
//        testBCEL();

    }
}
