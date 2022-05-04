package com.lhp.thread.callable;

import java.util.concurrent.*;

/**
 * @author Amumu
 * @create 2019/10/17 22:03
 *
 * 创建线程的2种方式，一种是直接继承Thread，另外一种就是实现Runnable接口。
 * 这2种方式都有一个缺陷就是：在执行完任务之后无法获取执行结果。
 * 如果需要获取执行结果，就必须通过共享变量或者使用线程通信的方式来达到效果，这样使用起来就比较麻烦。
 * 而自从Java 1.5开始，就提供了Callable和Future，通过它们可以在任务执行完毕之后得到任务执行结果
 *
 */
//泛型 指定返回值类型 ，相比于runable此接口可以获得返回值
class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("MyCallable---"+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //class FutureTask<V> implements RunnableFuture<V>
        //public FutureTask(Callable<V> callable)
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyCallable());
        new Thread(integerFutureTask, "AAA").start();
        //用同一个FutureTask构造出来的thread只会执行一次,可以理解为，这是一个任务，第一个抢占到可以执行它的线程完成后，
        // 后面的线程就不需要再次进行运算
        new Thread(integerFutureTask, "BBB").start();
        //integerFutureTask.get(1,TimeUnit.SECONDS);//如果超时未完成就i会抛异常
        //获取执行结果（尽量放在程序末尾，给这个线程充足的时间让其执行）
        Integer integer = integerFutureTask.get();
        System.out.println("integer = " + integer);

    }
}
