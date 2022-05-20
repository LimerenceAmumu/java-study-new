package com.lhp.thread.oom;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/5/5 15:00
 */
public class TestClient1 {

    @SneakyThrows
    public static void main(String[] args) {
        //test1();
        System.out.println("========================");
        test2();
    }

    /**
     * 耗时最久的-10s异步任务最先进入list执行，所以在循环过程中获取这个10s的任务结果的时候，get操作会一直阻塞，
     * 直到10s异步任务执行完毕。即使 3s、5s的任务早就执行完了，也得阻塞等待10s任务执行完。
     *
     * @throws Exception
     */
    public static void test1() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futureArrayList = new ArrayList<>();
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        Future<String> future10 = executorService.submit(() -> {
            System.out.println("总裁： 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";

        });
        futureArrayList.add(future10);
        Future<String> future3 = executorService.submit(() -> {
            System.out.println("研发： 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        futureArrayList.add(future3);
        Future<String> future6 = executorService.submit(() -> {
            System.out.println("中层管理： 要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        futureArrayList.add(future6);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        try {
            for (Future<String> future : futureArrayList) {
                String returnStr = future.get();//阻塞等待。 先通知到总裁，也是先接总裁 足足等了1个小时，接到总裁后再去接研发和中层管理，尽管他们早就完事儿了，也得等总裁拉完~~
                System.out.println(returnStr + "，你去接他");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test2() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        completionService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        //提交了3个异步任务）
        for (int i = 0; i < 3; i++) {
            String returnStr = completionService.take().get(); //必须要调用
            System.out.println(returnStr + "，你去接他");
        }
    }


}
