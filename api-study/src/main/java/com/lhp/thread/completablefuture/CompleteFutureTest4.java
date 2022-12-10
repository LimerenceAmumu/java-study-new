package com.lhp.thread.completablefuture;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/7/20 16:18
 */
public class CompleteFutureTest4 {

    public static void main(String[] args) {
        //记录开始时间
        Long start = System.currentTimeMillis();

        //定长10线程池
        ExecutorService executor = Executors.newFixedThreadPool(6);

        //任务
        List<Integer> taskList = IntStream.rangeClosed(1, 50).boxed().collect(Collectors.toList());
//            final List<Integer> taskList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<String> resultList = new CopyOnWriteArrayList<>();

        Map<String, String> errorList = new ConcurrentHashMap<>();


        otherTest(start, executor, taskList, resultList, errorList);

    }

    private static void otherTest(Long start, ExecutorService executor, List<Integer> taskList, List<String> resultList, Map<String, String> errorList) {
//        Stream<CompletableFuture<String>> completableFutureStream = taskList.stream()
//                .map(num -> {
//                            return CompletableFuture
//                                    .supplyAsync(() ->  getDouble(num), executor)
//                                    //任务失败或者完成都会到达这里
//                                    .handle((s, throwable) -> {
//
//                                        if (throwable == null) {
//                                            System.out.println("任务" + num + "完成! result=" + s + ", " + new Date());
//                                            resultList.add(s.toString());
//                                        } else {
//                                            System.out.println("任务" + num + "异常! e=" + throwable + ", " + new Date());
//                                            errorList.put(num.toString(), throwable.getMessage());
//                                        }
//                                        return "";
//                                    });
//                        }
//                );

        Stream<Object> completableFutureStream = taskList.stream()
                .map(num -> CompletableFuture.supplyAsync(() -> getDouble(num), executor)
                        .whenComplete((s, t) -> {//相比于  handle 它不会处理异常,会直接在日志中打印
                            if (t != null) {
                                System.out.println("任务" + num + "异常! e=" + t + ", " + new Date());
                                errorList.put(s.toString(), t.getMessage());
                            } else {
                                resultList.add(s.toString());
                                System.out.println("任务" + num + "完成! result=" + s + ", " + new Date());
                            }
                        })
                        .exceptionally(t ->
                                -1
                        )
                );


        CompletableFuture[] completableFutures = completableFutureStream.toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(completableFutures)
                .whenComplete((v, th) -> {
                    System.out.println("所有任务执行完成触发\n resultList=" + resultList + "\n errorList=" + errorList + "\n耗时=" + (System.currentTimeMillis() - start));

                }).join();// whenComplete: 这里不加   join()  就看不到异常    handle: handle会处理异常不会抛出  ;

        System.out.println("end");
    }

    //根据数字判断线程休眠的时间
    public static Integer getDouble(Integer i) {
        try {
            Thread.sleep(500);
            if (i == 1) {
                //任务1耗时3秒
                //   Thread.sleep(3000);
            } else if (i == 2) {
                //任务2耗时1秒,还出错
                //   Thread.sleep(1000);
                throw new RuntimeException("出异常了==========");
            } else {
                //其它任务耗时1秒
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2 * i;
    }
}

