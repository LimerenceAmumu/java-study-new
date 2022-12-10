package com.lhp.thread.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/7/19 11:04
 */
public class MyDemoLockCondition {
    // 锁
    private static final Lock lock = new ReentrantLock();

    // pool中有数据时的条件
    private static Condition full = lock.newCondition();

    // pool中没有数据时的条件
    private static Condition empty = lock.newCondition();

    // 模拟一个缓冲区，可以看到缓冲区是不可并发的
    private static List<String> pool = new LinkedList<String>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new ADD(), " Add :" + i)
                    .start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Remove(), "Remove:" + i)
                    .start();
        }

    }


    static class ADD implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (pool.size() == 16) {
                        System.out.println(Thread.currentThread().getName() + "在等待,因为缓存满了");
                        full.await();// 挂起当前线程
                    }
                    pool.add("size:" + pool.size());
                    System.out.println(Thread.currentThread().getName() + "生产了一个,并唤醒了消费者");

                    empty.signalAll();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }


        }
    }

    static class Remove implements Runnable {


        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {

                    while (pool.size() == 0) {
                        System.out.println(Thread.currentThread().getName() + "在等待,因为没吃的");

                        empty.await();
                    }
                    pool.remove(pool.size() - 1);
                    System.out.println(Thread.currentThread().getName() + "消费了一个,并唤醒了 生产者");

                    System.out.println("");
                    full.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

