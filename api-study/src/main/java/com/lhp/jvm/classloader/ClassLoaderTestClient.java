package com.lhp.jvm.classloader;

import org.junit.Test;

/**
 * @Description: 类加载器
 * @author: lihp
 * @date: 2021/10/18 16:44
 */
public class ClassLoaderTestClient {
    /**
     * 三种获取到的是同一个
     *
     * @throws ClassNotFoundException
     */
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


    /**
     * 根加载器无法通过代码直接获取
     * 同时我们通过获取String类型的加载器，发现是null，那么说明String类型是通过根加载器进行加载的，也就是说Java的核心类库都是使用根加载器进行加载的。
     */
    @Test
    public void test() {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);

        //获取其上层的:扩展类加载器
        ClassLoader classLoaderParent = systemClassLoader.getParent();//sun.misc.Launcher$ExtClassLoader@668bc3d5

        System.out.println(classLoaderParent);

        //获取根加载器
        ClassLoader loaderParentParent = classLoaderParent.getParent(); //null
        System.out.println(loaderParentParent);

        //获取自定义加载器
        ClassLoader loader = ClassLoaderTestClient.class.getClassLoader();//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(loader);

        //获取String类型的加载器
        ClassLoader classLoader = String.class.getClassLoader();//null
        System.out.println(classLoader);
    }

    @Test
    public void test3() {
        String property = System.getProperty("java.ext.dirs");
        System.out.println("property = " + property);
        String property1 = System.getProperty("java.class.path");
        System.out.println("property1 = " + property1);
    }

    @Test
    public void test4() {
        String ss = "ssss";
    }

}
