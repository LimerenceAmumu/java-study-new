package com.lhp.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * thenRun/thenRunAsync
 * 如果你执行第一个任务的时候，传入了一个自定义线程池：
 * <p>
 * 调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池。
 * 调用thenRunAsync执行第二个任务时，则第一个任务使用的是你自己传入的线程池，第二个任务使用的是ForkJoin线程池
 * <p>
 * TIPS: 后面介绍的thenAccept和thenAcceptAsync，thenApply和thenApplyAsync等，它们之间的区别也是这个哈。
 */
public class CompletableFutureTest3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("先执行第一个CompletableFuture方法任务");
                    return "1111111111";
                }
        );

        CompletableFuture thenRunFuture = orgFuture.thenRun(() -> {
            try {
                String s = orgFuture.get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("接着执行第二个任务");
        });

//        System.out.println(orgFuture.get());
        System.out.println(thenRunFuture.get());
        System.out.println(thenRunFuture.join());
    }

}
