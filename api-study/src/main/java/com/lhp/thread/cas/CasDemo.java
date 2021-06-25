package com.lhp.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Amumu
 * @create 2019/10/19 15:14
 * CAS 比较并交换 compareAndSet
 *
 * 原理：
 *  自旋锁
 *  UnSafe类
 */
public class CasDemo {

    public static void main(String[] args) {
        CasDemo();
    }

    /**
     * CAS 功能的基本演示
     */
    public static void CasDemo() {
        AtomicInteger atomicInteger= new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 20)+"修改后的值---"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 30)+"修改后的值---"+atomicInteger.get());
    }
    /**
     * CAS原理
     */
    public static void CasDemo1() {
        AtomicInteger atomicInteger= new AtomicInteger(5);
        atomicInteger.getAndIncrement();
        System.out.println("atomicInteger = " + atomicInteger);

    }
}
