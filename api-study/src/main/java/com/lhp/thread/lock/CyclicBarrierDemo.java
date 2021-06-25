package com.lhp.thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Amumu
 * @create 2019/9/24 16:11
 * CyclicBarrier 召唤神龙做加法
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println("召唤神龙=======");
        });

        for (int i = 1; i <= 4; i++) {
            new Thread(()->{

                System.out.println(Thread.currentThread().getName()+"到了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, CountryEnum.foreach(i).name()).start();
        }
    }
}
