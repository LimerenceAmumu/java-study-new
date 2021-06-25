package com.lhp.thread.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author Amumu
 * @create 2019/9/24 15:26
 * CountDownLatch 秦灭六国 做减法
 */

public class CoutDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 1; i <= 4; i++) {
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"亡国");
                countDownLatch.countDown();
            }, CountryEnum.foreach(i).name()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"统一");

    }
}
