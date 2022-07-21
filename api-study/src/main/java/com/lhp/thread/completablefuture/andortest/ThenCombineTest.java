package com.lhp.thread.completablefuture.andortest;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ThenCombineTest {

    @SneakyThrows
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        CompletableFuture<String> first = CompletableFuture.supplyAsync(ThenCombineTest::get);
        ExecutorService executor = Executors.newFixedThreadPool(10);
//        CompletableFuture<String> future = CompletableFuture
//                //第二个异步任务
//                .supplyAsync(() -> "第二个异步任务", executor)
//                // (w, s) -> System.out.println(s) 是第三个任务
//                .thenCombineAsync(first, (s, w) -> {
//                    System.out.println(w);
//                    System.out.println(s);
//                    return "两个异步任务的组合";
//                }, executor);
//        System.out.println(future.join());
//        executor.shutdown();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(ThenCombineTest::get2)
                .thenCombineAsync(first, (o, t) -> {
                    System.out.println("o:" + o);
                    System.out.println("t:" + t);
                    return "两个异步任务的组合";
                });
        System.out.println(stringCompletableFuture.get());

    }

    @SneakyThrows
    private static String get() {
        TimeUnit.SECONDS.sleep(3);
        return "\"第一个异步任务\"";
    }

    @SneakyThrows
    private static String get2() {
        TimeUnit.SECONDS.sleep(1);
        return "第2个";
    }
}
