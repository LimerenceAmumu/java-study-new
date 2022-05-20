package com.lhp.thread.theadpoll;

import com.lhp.collections.juc.MyThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
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
                2,//核心线程数 初始值为0
                5,// 最大线程数,阻塞队列满了的时候会判断线程数是否已经达到max,小于的话增加工作线程立即处理该任务(阻塞队列中的任务继续等待),否则执行拒绝策略
                30L,// 空闲线程存活时间
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                new MyThreadFactory(),//主要用来 线程命名
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
        // 设置为true的话  即便是coreThread 空闲时间超过存活时间也会回收
        boolean b = threadPoolExecutor.allowsCoreThreadTimeOut();
        try {
            //   TimeUnit.SECONDS.sleep(20);

            //模拟
            for (int i = 1; i <= 10; i++) {
                int finalI = i;
                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "处理中---index:" + finalI);
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
