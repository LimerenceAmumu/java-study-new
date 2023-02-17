package com.lhp.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * supplyAsync: 支持返回值
 * //使用默认内置线程池ForkJoinPool.commonPool()，根据supplier构建执行任务
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * //自定义线程，根据supplier构建执行任务
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 * <p>
 * <p>
 * runAsync: 无返回值
 * //使用默认内置线程池ForkJoinPool.commonPool()，根据runnable构建执行任务
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * //自定义线程，根据runnable构建执行任务
 * public static CompletableFuture<Void> runAsync(Runnable runnable,  Executor executor)
 */
public class CompletableFutureTest2 {

    public static void main(String[] args) {
        //可以自定义线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //runAsync的使用    无返回值
        CompletableFuture<Void> runFuture = CompletableFuture.runAsync(() -> System.out.println("run==========="), executor);
        //supplyAsync的使用  支持返回值
        CompletableFuture<String> supplyFuture = CompletableFuture.supplyAsync(() -> {
            System.out.print("supply========");
            return "我是返回值";
        }, executor);
        //runAsync的future没有返回值，输出null

        System.out.println(runFuture.join());
        //supplyAsync的future，有返回值
        System.out.println(supplyFuture.join());
        executor.shutdown(); // 线程池需要关闭
    }


}
