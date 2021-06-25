package com.lhp.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Amumu
 * @create 2019/9/24 12:51
 * 可重用读写锁 读写分离
 */
class MyCache{
    Map<String,String> map=new HashMap<String,String>();
    ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    void put(String key,String value){
        reentrantReadWriteLock.writeLock().lock();
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"插入完成");
        reentrantReadWriteLock.writeLock().unlock();
    }
    void get (String key){
        reentrantReadWriteLock.readLock().lock();
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"获取到了"+o.toString());
        reentrantReadWriteLock.readLock().unlock();
    }
}

public class ReadWriteLockDemo {


    public static void main(String[] args) {

        MyCache myCache=new MyCache();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                myCache.put(finalI+"",finalI+"");

            },i+"").start();
        }

    }

}
