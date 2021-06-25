package com.lhp.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Amumu
 * @create 2019/10/21 19:23
 * 传统版本的 生产者消费者模式
 */
class MyResources{
    int  num=0;
    ReentrantLock lock=new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increcment() throws InterruptedException {
        lock.lock();
        while (num!=0){
            //do nothing
            condition.await();
        }
        num++;
        System.out.println("增加完成"+Thread.currentThread().getName()+num);
        condition.signalAll();
        lock.unlock();
    }
    public void decrecment() throws InterruptedException {
        lock.lock();
        while (num==0){
            //do nothing
            condition.await();
        }
        num--;
        System.out.println("减少完成"+Thread.currentThread().getName()+num);
        condition.signalAll();
        lock.unlock();
    }


}

public class ProducterCustomerDemo_trans {
    public static void main(String[] args) {
        MyResources myResources = new MyResources();
         new Thread(()->{
             try {
                 for (int i = 0; i < 5; i++) {
                     myResources.increcment();
                 }
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },"AA").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 5; i++) {
                    myResources.decrecment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 5; i++) {
                    myResources.increcment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CC").start();

        new Thread(()->{
            try {
                for (int i = 0; i < 5; i++) {
                    myResources.decrecment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"DD").start();

    }
}
