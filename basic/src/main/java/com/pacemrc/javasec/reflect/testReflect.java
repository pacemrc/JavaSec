package com.pacemrc.javasec.reflect;

import java.lang.reflect.*;

/**
 * REFERNCE
 * https://www.bilibili.com/video/BV1k64y1Y73P?p=1&vd_source=12fd51019b9ffc7fda3230e04dc3b344
 */
public class testReflect {

    public static void main(String[] args) throws Exception {
        //反射访问属性
        getClassTest();
        //反射创建对象
        getClassConstructor();
        //反射获取属性
        getClassField();
        //反射访问方法
        getClassMethod();
        //访问父类接口
        accessToParentClassInterface();
    }

    /**
     * 通过反射访问一个对象的属性测试
     *
     * @throws ClassNotFoundException 类没有发现异常
     */
    public static void getClassTest() throws Exception {
        System.out.println("1、通过反射获取类对象");
        User user = new User();
        String pojoPath = "com.pacemrc.javasec.reflect.User";
        //第一种获取类对象的方法
        Class<User> clzObj1 = User.class;
        //第二种获取类对象的方法
        Class<? extends User> clzObj2 = user.getClass();
        //第三种获取类对象的方法
        Class<?> clzObj3 = Class.forName(pojoPath);
        System.out.println("方法一获取的类对象：" + clzObj1);
        System.out.println("方法二获取的类对象：" + clzObj2);
        System.out.println("方法三获取的类对象：" + clzObj3);

    }

    /**
     * 通过反射获取类构造函数   通过反射机制创建一个对象
     */
    public static void getClassConstructor() throws Exception {
        System.out.println("2、通过反射获取类构造方法");
        System.out.println("2.1、获取无参构造方法创建一个对象");
        String pojoPath = "org.demo.User";
        //获取类
        Class<?> userClass = Class.forName(pojoPath);

        //获取无参构造方法创建一个对象
        Constructor<?> constructor = userClass.getConstructor();
        //创建一个实例 userTest现在是一个对象
        Object userTest = constructor.newInstance();
        System.out.println(userTest);

        //获取有参构造方法创建一个对象
        System.out.println("2.2、获取有参构造方法创建一个对象");
        Constructor<?> constructor1 = userClass.getConstructor(String.class, String.class);
        Object o = constructor1.newInstance("pacemrc", "123456");
        System.out.println(o);

    }

    /**
     * 通过反射获取类属性 对属性的操作有：获取权限修饰符，获取数据类型，获取属性名，设置属性值，获取属性值
     */
    public static void getClassField() throws Exception {
        System.out.println("3、通过反射获取类属性");
        //获取类
        String pojoPath = "com.pacemrc.javasec.reflect.User";
        Class<?> clzObj4 = Class.forName(pojoPath);
        //获取类对象中属性的所有属性
        Field[] declaredFields = clzObj4.getDeclaredFields();  //getDeclaredFields()为获取所有属性，getDeclaredField(FieldName)为获取指定属性
        System.out.println("3.1、通过反射获取所有属性名");
        for (Field field:declaredFields){
            System.out.println(field.getName());
        }
        //获取类对象中属性的权限修饰符
        System.out.println("3.2、通过反射获取属性的权限修饰符");
        for (Field field:declaredFields){
            //通过Modifier.isPrivate方法判断是否为Private权限修饰符
            System.out.println(field.getName() +"的修饰符是否为Private：" + Modifier.isPrivate(field.getModifiers()));
        }
        //获取类对象中属性的数据类型
        System.out.println("3.3、通过反射获取属性的数据类型");
        for (Field field:declaredFields){
            System.out.println(field.getName() + "的数据类型："+ field.getType());
        }
        //设置类对象中的属性值（需先创建一个对象）
        //获取无参构造方法创建一个对象
        System.out.println("3.4、通过反射设置和获取类对象实例化对象的属性值");
        Constructor<?> constructor = clzObj4.getConstructor();
        Object userTest = constructor.newInstance();
        System.out.println("通过反射获取无参构造方法创建一个对象\n设置属性值前的对象：" + userTest);
        //设置和获取公开属性值
        declaredFields[0].set(userTest, "pacemrc");
        Object o1= declaredFields[0].get(userTest);
        System.out.println("设置公开属性"+ declaredFields[0].getName() + "值后获取属性值" + o1);
        System.out.println("设置属性值后的对象：" + userTest);

        //设置和获取私有属性值
        declaredFields[1].setAccessible(true);
        declaredFields[1].set(userTest, "123456");
        Object o2 = declaredFields[1].get(userTest);
        System.out.println("设置私有属性"+ declaredFields[1].getName() + "值后获取属性值" + o2);
        System.out.println("设置属性值后的对象：" + userTest);




    }

    /**
     * 通过反射获取类方法 对类方法的操作有：获取方法名、获取方法的权限修饰符、获取方法的返回值、获取方法的参数类型、参数个数、调用方法执行
     */
    public static void getClassMethod() throws Exception {
        System.out.println("4、通过反射获取方法");
        //获取类
        String pojoPath = "com.pacemrc.javasec.reflect.User";
        Class<?> clzObj5 = Class.forName(pojoPath);
        //获取类对象中所有方法
        Method[] methods = clzObj5.getDeclaredMethods();
        System.out.println("4.1、通过反射获取类对象中所有方法");
        for (Method method:methods){
            System.out.println(method.getName());
        }
        //获取方法中的访问修饰符
        System.out.println("4.2、通过反射获取类对象中方法的访问修饰符");
        for (Method method:methods){
            System.out.println(method.getName()+ "方法的权限修饰符是否为Public：" +Modifier.isPublic(method.getModifiers()));
        }
        //获取方法的返回值类型
        System.out.println("4.3、通过反射获取类对象中方法的返回值类型");
        for (Method method:methods){
            System.out.println(method.getName()+ "方法的返回值类型是：" + method.getReturnType().getName());
        }
        //获取方法的参数数量
        System.out.println("4.4、通过反射获取类对象中方法的参数个数");
        for (Method method:methods){
            System.out.println(method.getName()+ "方法的参数数量是：" + method.getParameterCount());
        }
        //获取方法的参数类型
        System.out.println("4.5、通过反射获取类对象中方法的参数类型");
        for (Method method:methods){
            Class<?>[] types = method.getParameterTypes();
            for (Type type:types){
                System.out.println(method.getName()+ "方法的参数类型是：" + type.getTypeName());
            }
        }
        //通过反射调用方法
        System.out.println("4.6、通过反射调用类对象示例中指定的方法sayHello");
        Constructor<?> constructor = clzObj5.getDeclaredConstructor();
        Object o = constructor.newInstance();
        Method sayHello = clzObj5.getDeclaredMethod("sayHello", User.class);
        sayHello.invoke(o,o);
    }

    /**
     * 访问父类接口
     */
    public static void accessToParentClassInterface() throws ClassNotFoundException {
        System.out.println("5、通过反射获取当前对象的父类的接口");
        //获取类
        String pojoPath = "com.pacemrc.javasec.reflect.User";
        Class<?> clzObj6 = Class.forName(pojoPath);
        System.out.println("5.1、获取当前类对象的父类");
        Class<?> superclass = clzObj6.getSuperclass();
        System.out.println("类对象" + clzObj6 + "的父类是"+ superclass.getName());
        System.out.println("5.2、通过反射获取类对象的父类实现的接口");
        Class<?>[] interfaces = superclass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

    }
}
