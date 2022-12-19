package com.lhp.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Amumu
 * @create 2019/10/19 21:53
 */
public class ABADemo {
    //普通的原子类
    static AtomicInteger atomicInteger=new AtomicInteger(10);

    //带版本号的原子类引用
    static AtomicStampedReference<Integer> stampedReference=new AtomicStampedReference<Integer>(10,1);
    public static void main(String[] args) {
        //testNormal();
        testABA();

    }
    /**
     * ABA问题的演示
     *  两个线程
     */
    static public void testNormal(){

        new Thread(() -> {
            atomicInteger.compareAndSet(10, 11);
            atomicInteger.compareAndSet(11, 10);
        }, "LockSupportDemo4").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);//sleep2秒等待T1 执行完毕
                System.out.println("T2是否执行成功" + atomicInteger.compareAndSet(10, 12) + "当前值==" + atomicInteger.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();

    }

    /**
     * ABA 问题解决
     *
     */
    static void testABA(){
         new Thread(()->{
             int stamp = stampedReference.getStamp();//获取当前版本号
             System.out.println(Thread.currentThread().getName()+"当前版本号"+stamp);
             //等待T4线程
             try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             //版本号每次都加1
             stampedReference.compareAndSet(10,11,1,stampedReference.getStamp()+1);
             stampedReference.compareAndSet(11,10,2,stampedReference.getStamp()+1);
             System.out.println(Thread.currentThread().getName()+"当前版本号"+stampedReference.getStamp());

                 },"T3").start();
        new Thread(()->{
            int stamp = stampedReference.getStamp();//获取当前版本号
            System.out.println(Thread.currentThread().getName()+"当前版本号"+stamp);
            try {//等待T3线程 执行完ABA
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //执行更新操作 但是由于版本号失败所以 会出现 操作失败
            System.out.println(Thread.currentThread().getName()+"更新结果"
                    +stampedReference.compareAndSet(10,20,stamp,stamp+1));
            System.out.println(Thread.currentThread().getName()+"当前版本号"+stampedReference.getStamp());

        },"T4").start();

    }

}
