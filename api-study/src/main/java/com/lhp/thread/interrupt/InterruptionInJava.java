package com.lhp.thread.interrupt;

/**
 * 中断在java中主要有3个方法，interrupt(),isInterrupted()和interrupted()。
 * <p>
 * interrupt()，在一个线程中调用另一个线程的interrupt()方法，即会向那个线程发出信号——线程中断状态已被设置。至于那个线程何去何从，由具体的代码实现决定。<p>
 * isInterrupted()，用来判断当前线程的中断状态(true or false)。<p>
 * interrupted()是个Thread的static方法，用来恢复中断状态。<p>
 * <br>
 * interrupt()不能中断在运行中的线程，它只能改变中断状态而已。
 * 被中断后，仍旧运行，不停打印Yes,I am interruted,but I am still running
 *
 * @Description:
 * @author: lihp
 * @date: 2022/4/29 14:32
 */
public class InterruptionInJava implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava(), "InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        //interrupt thread
        testThread.interrupt();
        System.out.println("main end");
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Yes,I am interruted,but I am still running");

            } else {
                System.out.println("not yet interrupted");
            }
        }
    }


}
