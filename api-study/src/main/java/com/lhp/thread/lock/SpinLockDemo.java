package com.lhp.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Amumu
 * @create 2019/9/24 10:16
 * 自旋锁
 *  减少了线程间上下文切换的时间
 *  增加了线程自旋时候的cpu损耗
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicThread = new AtomicReference<>();
    public void myLock(){
        Thread thread = Thread.currentThread();
        //进来的线程会判断是否是期望的  那样 实际值为空，如果是的话修改值为自己，不进入循环，如果实际值不是空那么进入死循环
        while(!atomicThread.compareAndSet(null,thread)){

            System.out.println(thread.getName()+"正在自旋");
        }
        System.out.println(thread.getName()+"获得🔒");
    }
    public void unLock(){
        Thread thread = Thread.currentThread();
        //解锁操作，如果发现锁是被自己占用（实际值是自己），释放锁（把实际值置为空）
        atomicThread.compareAndSet(thread,null);
        System.out.println(thread.getName()+"释放🔒");
    }

    public static void main(String[] args) throws InterruptedException {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{

            try {
                spinLockDemo.myLock();
                TimeUnit.SECONDS.sleep(5);
                spinLockDemo.unLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        //保证AA线程先获得锁
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{

            try {
                spinLockDemo.myLock();
                TimeUnit.SECONDS.sleep(5);
                spinLockDemo.unLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
