package com.lhp.thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Amumu
 * @create 2019/9/24 16:11
 * CyclicBarrier  三阶段 第一阶段 所有线程准备好之后即 cyclicBarrier.await(); 调用次数到达  parties 后
 *                       第二阶段 执行 Runnable barrierAction  可以为null
 *                       第三阶段  执行 子线程的任务 ,之前调用 cyclicBarrier.await(); 后被 阻塞. 现在继续执行
 *
 *                       1,CountdownLatch适用于所有线程通过某一点后通知方法,而CyclicBarrier则适合让所有线程在同一点同时执行 2,CountdownLatch利用继承AQS的共享锁来进行线程的通知,利用CAS来进行--,而CyclicBarrier则利用ReentrantLock的Condition来阻塞和通知线程
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println("所有人都准备好了裁判开始了");
        });
        for (int i = 0; i < 4; i++) {
            //lambda中只能只用final的变量
            final int times = i;
            new Thread(() -> {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在准备");
                    Thread.sleep(200 * times);
                    System.out.println("子线程" + Thread.currentThread().getName() + "准备好了");
                    cyclicBarrier.await();
                    System.out.println("子线程" + Thread.currentThread().getName() + "开始跑了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
