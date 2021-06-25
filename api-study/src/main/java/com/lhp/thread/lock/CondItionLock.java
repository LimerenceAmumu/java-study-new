package com.lhp.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Amumu
 * @create 2019/10/21 22:31
 * 线程之间的合作
 * 线程1 打印5次
 * 线程2 打印10次
 * 线程3 打印15次
 */
class MyRes {
    volatile int num = 1;
    ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5() {
        try {
            lock.lock();
            //判断
            while (num != 1) {
                condition1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            //通知
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }

    public void print10() {

        try {
            lock.lock();
            //判断
            while (num != 2) {
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            //通知
            num = 3;
            condition3.signal();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            lock.unlock();
        }
    }

    public void print15() {

        try {
            lock.lock();
            //判断
            while (num != 3) {
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
            //通知
            num = 1;
            condition1.signal();

        } catch (Exception e) {

        } finally {

            lock.unlock();
        }
    }
}

public class CondItionLock {
    public static void main(String[] args) {

        MyRes myRes = new MyRes();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                myRes.print5();
            }, "T5---" + i).start();

        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                myRes.print10();
            }, "T10---" + i).start();

        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                myRes.print15();
            }, "T15---" + i).start();

        }

    }
}
