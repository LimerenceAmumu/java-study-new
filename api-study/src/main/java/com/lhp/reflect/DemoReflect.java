package com.lhp.reflect;


import com.lhp.bean.Person;

/**
 * @author Amumu
 * @create 2019/7/24 8:01
 * 获取Class对象的三种方式
 *      1.Class.forName(全类名)
 *      2.类名.class
 *      3.obj.getClass();
 */
public class DemoReflect {
    public static void main(String[] args) throws ClassNotFoundException {
       //1.Class.forName(全类名)
        Class aClass = Class.forName("com.lhp.domain.Person");
        System.out.println("aClass = " + aClass);
        //2.类名.class
        Class<Person> personClass = Person.class;
        System.out.println("personClass = " + personClass);
        //3.obj.getClass();
        Person person = new Person();
        Class<? extends Person> aClass1 = person.getClass();
        System.out.println("aClass1 = " + aClass1);
        //验证是否Class只加载一次
        System.out.println(aClass==personClass);
        System.out.println(aClass==aClass1);

    }
}
