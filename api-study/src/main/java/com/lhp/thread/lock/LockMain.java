package com.lhp.thread.lock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gavin
 */
public class LockMain {
    // 锁
    private static final Lock lock = new ReentrantLock();

    // pool中有数据时的条件
    private static Condition full = lock.newCondition();

    // pool中没有数据时的条件
    private static Condition empty = lock.newCondition();

    // 模拟一个缓冲区，可以看到缓冲区是不可并发的
    private static List<String> pool = new LinkedList<String>();

    // 生成add对象的唯一编号
    private static AtomicInteger addThreadNumber = new AtomicInteger(0);

    // 生成remove对象的唯一编号
    private static AtomicInteger removeThreadNumber = new AtomicInteger(0);

    // 生产的数据编号
    private static AtomicLong atomicLong = new AtomicLong(0);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
        // 这里可以模拟各种情况
        // 例如1:1/1:N/N:1/N:N 的生产者消费者数量
        for (int i = 0; i < 2; i++) {
            executor.execute(new Add());
        }
        for (int i = 0; i < 10; i++) {
            executor.execute(new Remove());
        }

    }

    //生产者
    static class Add implements Runnable {

        public void run() {
            int number = addThreadNumber.addAndGet(1);
            int i = 10;//每个生产者最多生产10个
            while (i != 0) {
                lock.lock();//必须要放在try{}外部!!!
                try {
                    System.out.println(String.format("%s%s 获取了锁", "Add", number));
                    // 这里可以减缓一下运行速度，方便debug
//                    try {
//                        Thread.sleep((long) (Math.random() * 1000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    try {//  池子不为空=6 就等待
                        while (pool.size() != 0) {
                            System.out.println(String.format("%s%s 等待池子变空", "Add", number));
                            empty.await();//等待变空
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    pool.add(String.format("Add线程%s 创建了 %s 号物品", number, atomicLong.addAndGet(1)));

                    full.signal();//生产了 东西,消费者搞起来!
                    //System.out.println(String.format("%s%s 发送已经变满", "Add", number));
                } finally {
                    lock.unlock();
                    //System.out.println(String.format("%s%s 释放了锁", "Add", number));
                }

                i--;
            }
        }
    }

    static class Remove implements Runnable {
        public void run() {
            int number = removeThreadNumber.addAndGet(1);
            while (true) {
                lock.lock();
                try {
                    System.out.println(String.format("%s%s 获取了锁", "Remove", number));
                    // 这里可以减缓一下运行速度，方便debug
//                    try {
//                        Thread.sleep((long) (Math.random() * 1000));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    try {
                        while (pool.size() == 0) {
                            System.out.println(String.format("%s%s 等待池子变满", "Remove", number));
                            full.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //pool size =2
                    System.out.println((String.format("Remove线程%s 获取了 %s", number, pool.remove(0))));

                    empty.signal();// 我饿了 生产者搞起来
                    //System.out.println(String.format("%s%s 发送可能变空信号", "Remove", number));
                } finally {
                    lock.unlock();
                    //System.out.println(String.format("%s%s 释放了锁", "Remove", number));
                }

            }
        }
    }
}