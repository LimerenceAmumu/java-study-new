package com.lhp.thread.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Amumu
 * @create 2019/9/24 16:47
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    //停了两秒
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"释放车位");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();

                }
            },"i") .start();
        }

    }
}
