package com.lhp.thread.theadpoll;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author Amumu
 * @create 2019/11/1 15:36
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                //抛异常的拒绝策略RejectedExecutionException
                //new ThreadPoolExecutor.AbortPolicy()
                //把任务交还给调用此线程的线程去执行
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //抛弃队列中等待最久的任务，然后把自己加入队列
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                //直接丢弃任务，如果任务可用丢弃这是最好的策略
                //new ThreadPoolExecutor.DiscardPolicy()
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            //模拟十个任务 需要开启10个线程
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "处理中---");
                    try {
                        TimeUnit.SECONDS.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

        //cpu核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

    }

    /**
     * 理想的线程数，使用 2倍cpu核心数
     */
    public static int desiredThreadNum() {
        return Runtime.getRuntime().availableProcessors() * 2;
    }

}
