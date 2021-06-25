package com.lhp.thread.theadpoll;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Amumu
 * @create 2019/10/31 22:19
 */
public class NormolThreadpool {
    public static void main(String[] args) {
        String yeu = "办理业务";
        //定容的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //仅允许一个线程同时存在的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //不固定容量的线程（会随着任务数的增加而改变活跃线程数）
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("----------FixedThreadPool----------------");
        try {
            for (int i = 1; i <= 10; i++) {
                fixedThreadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + yeu);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //但凡涉及到池之类的资源 都要记得关闭
            fixedThreadPool.shutdown();
        }

        try {
            TimeUnit.SECONDS.sleep(1L);

        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("----------CacheThreadPool----------------");
        try {
            for (int i = 1; i <= 10; i++) {
                cachedThreadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + yeu);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //但凡涉及到池之类的资源 都要记得关闭
            cachedThreadPool.shutdown();
        }


        try {
            TimeUnit.SECONDS.sleep(1L);

        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("----------SingleThreadPool----------------");
        try {
            for (int i = 1; i <= 10; i++) {
                singleThreadExecutor.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + yeu);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //但凡涉及到池之类的资源 都要记得关闭
            singleThreadExecutor.shutdown();
        }
    }
}
