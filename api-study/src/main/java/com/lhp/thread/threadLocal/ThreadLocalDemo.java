package com.lhp.thread.threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * threadlocal 只能存储最新的值底层是一个内部类map  key为threadlocal实例,value 为 要保存的值
 * InheritableThreadLocal  子线程更新后不会覆盖父线程的map;之所以可以获取父线程的值是因为在 new thread() 的时候把父线程的 map传了进来,是两个值相同的map但不是一个对象,也互不影响
 * <p>
 * 涉及到的主要方法 ,属性
 * threadLocal.set(value):
 * 获取 ThreadLocal.ThreadLocalMap,即取出当前线程中的Thread.threadLocals 的值(InheritableThreadLocal 的话是inheritableThreadLocals,重写了getMap() 方法)
 * <p>
 * 如果ThreadLocalMap==null
 * 1.创建一个 ThreadLocal.ThreadLocalMap
 * 2.把这个map 赋值给 Thread.threadLocals (在InheritableThreadLocal 的话是inheritableThreadLocals, 重写了createMap())
 * 3.设置值  key为threadlocal实例,value 为 要保存的值
 * 如果map已经存在则会更新map的value
 * <p>
 * 关于Map
 * key 为ThreadLocal 实例  value 是要保存的值, map和Thread关联在一起threadLocals 或者 inheritableThreadLocals
 *
 * @Description:
 * @author: lihp
 * @date: 2022/2/22 14:28
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("123");
        threadLocal.set("456");
        String s = threadLocal.get();

        InheritableThreadLocal<String> stringInheritableThreadLocal = new InheritableThreadLocal<>();
        stringInheritableThreadLocal.set("abc");
        stringInheritableThreadLocal.set("drf");
        new Thread(() -> {
            String s1 = stringInheritableThreadLocal.get();

            System.out.println(Thread.currentThread().getName() + "===" + s1);

            stringInheritableThreadLocal.set("ddd");
            s1 = stringInheritableThreadLocal.get();
            System.out.println(Thread.currentThread().getName() + "===" + s1);

        }, "thread B").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s1 = stringInheritableThreadLocal.get();
        System.out.println(Thread.currentThread().getName() + "===" + s1);


//        stringInheritableThreadLocal
    }
}
