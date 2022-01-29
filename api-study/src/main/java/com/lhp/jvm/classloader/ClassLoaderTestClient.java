package com.lhp.jvm.classloader;

import org.junit.Test;

/**
 * @Description: 类加载器测试
 * @author: lihp
 * @date: 2021/10/18 16:44
 */
public class ClassLoaderTestClient {
    @Test
    public void test1() throws ClassNotFoundException {
        // 通过Class对象获取
        Class<?> aClass = Class.forName("com.lhp.jvm.classloader.ClassLoaderTestClient");
        ClassLoader classLoader = aClass.getClassLoader();
        System.out.println("classLoader = " + classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        // 通过Thread 对象获取
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println("contextClassLoader = " + contextClassLoader);
        // 通过 ClassLoader.getSystemClassLoader()
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2
    }

}
