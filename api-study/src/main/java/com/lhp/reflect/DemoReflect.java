package com.lhp.reflect;


import com.lhp.bean.Person;
import org.junit.Test;

/**
 * @author Amumu
 * @create 2019/7/24 8:01
 * 获取Class对象的4种方式
 * 1.Class.forName(全类名)
 * 2.类名.class
 * 3.obj.getClass();
 * 4.通过类加载器xxxClassLoader.loadClass()传入类路径获取:
 */
public class DemoReflect {
    public static void main(String[] args) throws ClassNotFoundException {
        //1.Class.forName(全类名)
        Class aClass = Class.forName("com.lhp.domain.Person");//会执行静态代码块  不会执行非静态代码块
        System.out.println("aClass = " + aClass);
        //2.类名.class
        Class<Person> personClass = Person.class;// 不会执行静态代码块  不会执行非静态代码块
        System.out.println("personClass = " + personClass);
        //3.obj.getClass();
        Person person = new Person();
        Class<? extends Person> aClass1 = person.getClass();
        System.out.println("aClass1 = " + aClass1);
        //验证是否Class只加载一次
        System.out.println(aClass == personClass);
        System.out.println(aClass == aClass1);
    }

    @Test
    public void test() throws ClassNotFoundException {
        // Class<DemoClass> demoClassClass = DemoClass.class;//不执行静态代码块
        Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass("com.lhp.reflect.DemoClass");//不执行静态代码块
        //Class<?> aClass = Class.forName("com.lhp.reflect.DemoClass");//执行静态代码块
    }
}
