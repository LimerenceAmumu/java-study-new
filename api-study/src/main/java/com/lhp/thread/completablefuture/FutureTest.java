package com.lhp.thread.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 如果我们不使用Future进行并行异步调用，而是在主线程串行进行的话，耗时大约为300+500+300 = 1100 ms。
 * 可以发现，future+线程池异步配合，提高了程序的执行效率。
 * 但是Future对于结果的获取，不是很友好，只能通过阻塞或者轮询的方式得到任务的结果。
 * <p>
 * Future.get() 就是阻塞调用，在线程获取结果之前get方法会一直阻塞。
 * Future提供了一个isDone方法，可以在程序中轮询这个方法查询执行结果。
 * <p>
 * 阻塞的方式和异步编程的设计理念相违背，而轮询的方式会耗费无谓的CPU资源。
 * 因此，JDK8设计出CompletableFuture。CompletableFuture 提供了一种观察者模式类似的机制，可以让任务执行完成后通知监听的一方。
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();
        //调用用户服务获取用户基本信息  300ms
        FutureTask<UserInfo> userInfoFutureTask = new FutureTask<>(() -> userInfoService.getUserInfo(userId));
        executorService.submit(userInfoFutureTask);
        Thread.sleep(300); //模拟主线程其它操作耗时
        // 奖牌信息  500ms
        FutureTask<MedalInfo> medalInfoFutureTask = new FutureTask<>(() -> medalService.getMedalInfo(userId));
        executorService.submit(medalInfoFutureTask);
        UserInfo userInfo = userInfoFutureTask.get();//获取个人信息结果//阻塞
        MedalInfo medalInfo = medalInfoFutureTask.get();//获取勋章信息结果
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
        executorService.shutdown();
    }
}
