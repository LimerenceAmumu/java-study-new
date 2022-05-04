package com.lhp.thread.interrupt;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/29 16:05
 */
public class InterruptionInJava2 implements Runnable {
    private volatile static boolean on = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava2(), "InterruptionInJava2");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava2.on = true;
        testThread.interrupt();//如果线程被Object.wait, Thread.join和Thread.sleep三种方法之一阻塞，此时调用该线程的interrupt()方法，那么该线程将抛出一个 InterruptedException中断异常;并恢复中断状态
        System.out.println("main end");

    }

    @Override
    public void run() {
        while (!on) {
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                System.out.println("caught exception: " + e);
            }
        }
        System.out.println("线程中断状态" + Thread.currentThread().isInterrupted());
        System.out.println("Thread  is running ---");
    }
}
