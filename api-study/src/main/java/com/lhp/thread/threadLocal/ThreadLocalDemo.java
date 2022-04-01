package com.lhp.thread.threadLocal;

import java.util.concurrent.TimeUnit;

/**
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
