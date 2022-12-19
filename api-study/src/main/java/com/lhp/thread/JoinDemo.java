package com.lhp.thread;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/12/15 20:32
 */

public class JoinDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("T1");
        }, "T1");
        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2");
        }, "T2");
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T3");
        }, "T3");
        t3.start();
    }
}
