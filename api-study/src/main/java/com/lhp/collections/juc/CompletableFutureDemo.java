package com.lhp.collections.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/24 15:49
 */
class MyCallable implements Callable<Long> {


    @Override
    public Long call() throws Exception {
        Random random = new Random();
        long time = random.nextInt(10);

        //do sth...
        TimeUnit.SECONDS.sleep(time);
        System.out.println("time = " + time);
        System.out.println("执行结束:" + Thread.currentThread().getName());
        return time;
    }
}

public class CompletableFutureDemo {
    static ThreadPoolExecutor threadPoolExecutor = null;

    public static void main(String[] args) {

        initThreadPoll();

        FutureTask<Long> longFutureTask1 = new FutureTask<>(new MyCallable());
        threadPoolExecutor.submit(longFutureTask1);
        try {
            Long aLong = longFutureTask1.get();
            System.out.println("aLong = " + aLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        threadPoolExecutor.shutdown(); // 线程池需要关闭

    }

    public static final void initThreadPoll() {
        threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(500),
                new MyThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
    //

}
