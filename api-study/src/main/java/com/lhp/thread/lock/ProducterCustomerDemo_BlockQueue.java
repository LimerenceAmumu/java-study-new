package com.lhp.thread.lock;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Amumu
 * @create 2019/10/22 9:46
 */
class MyResous{
    volatile Boolean FLAG=true;//开启则生产 关闭则停止生产
    BlockingQueue<String> blockingQueue=null;
    //资源数量
    AtomicInteger atomicInteger = new AtomicInteger(0);
    //利用构造方法传入具体的阻塞队列实现方式实现
    public MyResous(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }
    public void add() throws InterruptedException {
        String data="";
        boolean rtn=false;
       while (FLAG){
           data=atomicInteger.incrementAndGet()+"";
           rtn = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
           if(rtn){

               System.out.println(Thread.currentThread().getName()+"===制作了一个蛋糕"+"当前数量是"+data);
           }else {
               System.out.println(Thread.currentThread().getName()+"===制作失败"+"当前数量是"+data);
           }
           TimeUnit.SECONDS.sleep(1);
       }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("大老板叫停");


    }
    public void decreasement() throws InterruptedException {
        String rtn="";
        //判断
        while (FLAG){

            rtn = blockingQueue.poll(2L, TimeUnit.SECONDS);

            if(null==rtn || Objects.equals(rtn,"")){
                //超过两秒未拿到蛋糕
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"超过两秒未拿到蛋糕");
                return;
            }

            atomicInteger.getAndDecrement();
            System.out.println(Thread.currentThread().getName()+"===消费了一个蛋糕"+"当前数量是"+atomicInteger.get());

        }
        //操作
        atomicInteger.getAndDecrement();
        blockingQueue.offer(atomicInteger.get()+"",2L, TimeUnit.SECONDS);
        System.out.println(Thread.currentThread().getName()+"===消费了一个蛋糕"+"当前数量是"+atomicInteger.get());
    }
    void stop(){
        FLAG=false;
    }
}

/**
 * 做蛋糕 一个生产者 一个消费者
 */
public class ProducterCustomerDemo_BlockQueue {
    public static void main(String[] args) throws InterruptedException {
        MyResous myResous = new MyResous(new ArrayBlockingQueue<String>(5));

        new Thread(() -> {
            try {
                myResous.add();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Product").start();


        new Thread(()->{
            try {
                myResous.decreasement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Customer").start();


        TimeUnit.SECONDS.sleep(3L);

        myResous.stop();
    }
}
