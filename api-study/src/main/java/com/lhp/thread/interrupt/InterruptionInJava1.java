package com.lhp.thread.interrupt;

/**
 * 这样会有一个缺点,线程被阻塞的时候无法被中断,否则会抛异常
 *
 * @Description:
 * @author: lihp
 * @date: 2022/4/29 14:32
 */
public class InterruptionInJava1 implements Runnable {
    private volatile static boolean on = false;

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava1(), "InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava1.on = true;

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
    }
}
