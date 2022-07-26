package com.lhp.thread.completablefuture;

import java.util.concurrent.*;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/25 14:33
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        UserInfoService userInfoService = new UserInfoService();
        MedalService medalService = new MedalService();
        long userId = 666L;
        long startTime = System.currentTimeMillis();
        //调用用户服务获取用户基本信息
        CompletableFuture<UserInfo> completableUserInfoFuture = CompletableFuture.supplyAsync(() -> userInfoService.getUserInfo(userId));
        Thread.sleep(300); //模拟主线程其它操作耗时
        CompletableFuture<MedalInfo> completableMedalInfoFuture = CompletableFuture.supplyAsync(() -> medalService.getMedalInfo(userId));
        UserInfo userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);//获取个人信息结果
        MedalInfo medalInfo = completableMedalInfoFuture.get();//获取勋章信息结果
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

        //****************分隔 和上面没关联*************************
        CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            int i = ThreadLocalRandom.current().nextInt(100);
            System.out.println("i = " + i);
            if (i > 2) {
                i = i / 0;
            }
            return i;
        });
        uCompletableFuture.whenComplete((v, e) -> {
            //必须要判断，因为 无论上一步任务是否发生异常都会进入到这里，e!=null 时 返回值是null
            if(e==null){
                System.out.println("获取到的上一步计算结果：" + v);
            }
        }).exceptionally((e) -> {
            System.out.println("发生了异常！");
            e.printStackTrace();
            return null;
        });
    }

}
