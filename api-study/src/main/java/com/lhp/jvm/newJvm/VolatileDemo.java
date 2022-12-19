package com.lhp.jvm.newJvm;

import java.util.concurrent.TimeUnit;

/**
 * @author Amumu
 * @create 2020/7/22 22:10
 * volatile 可见性验证
 */
class MyData {
   volatile int a = 0;
   // int a = 0;

    void addTo100() {
        this.a = 100;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in---");
            //静等3 秒
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 不加 volatile  并不会 通知其他线程 ，其他线程获得的值还是 0
            myData.addTo100();
            System.out.println(Thread.currentThread().getName() + "\t 修改a 的值为 \t" + myData.a);
        }, "LockSupportDemo4").start();
        //因为 线程T1  修改了 a的 值 但是 main线程并不知情 所以会在这里空转
        while (myData.a==0){

        }
        System.out.println("progrom exit---");
    }
}
