package com.lhp.thread.looksuport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock、unlock对里面才能正确调用调用condition中线程等待和唤醒的方法
 * <p>
 * #
 */
public class LockSupportDemo2 {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Condition condition2 = lock.newCondition();
//        try {
//            condition.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "start");
                condition.await();
                condition2.await();
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println("  condition.signal();");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "通知了");
        }, "t2").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition2.signal();
                System.out.println("  condition2.signal();");

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "通知了");
        }, "t3").start();


    }
}
