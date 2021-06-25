package com.lhp.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author Amumu
 * @create 2019/11/2 17:14
 */
class MyHoldThread implements Runnable{
    String lockA;
    String lockB;
    public MyHoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"拿到了"+lockA+"尝试获取"+lockB);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"拿到了"+lockB+"尝试获取"+lockA);
            }
        }

    }
}

public class Deadlock {
    public static void main(String[] args) {
        String lockA="LOCK_A";
        String lockB="LOCK_B";
        new Thread(new MyHoldThread(lockA,lockB),"threadA").start();
        new Thread(new MyHoldThread(lockB,lockA),"threadB").start();
    }
}
