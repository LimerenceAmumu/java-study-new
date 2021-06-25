package com.lhp.jvm.newJvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Amumu
 * @create 2019/10/19 14:45
 * Volatile无法保证原子性
 *
 * n个线程读取到 主内存当中的 值 并对其进行修改 ，
 * 都准备写入主物理内存 的时候 发生阻塞  a写入 的时候  b，c 阻塞
 * b 获取到cpu的时候立马写入 内存（只有在做读取操作时，发现自己缓存行无效，才会去读主存的值） 导致 写覆盖
 *
 */
class MyData1{
    volatile int a=0;
    //可以通过加 sync解决
    void add(){
        a++;
    }
    //==========================
    AtomicInteger atomicInteger=new AtomicInteger();//默认值为零
    void addAtomic(){
        atomicInteger.getAndIncrement();//i++
    }
}

public class VolatileAutomic {
    public static void main(String[] args) {
        MyData1 myData1 = new MyData1();
        for (int i = 1; i <= 20000; i++) {
             new Thread(()->{
                 myData1.add();
                 myData1.addAtomic();
                     },"T=="+i).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("不安全的Volatile++，a="+myData1.a);//期望值是20000
        System.out.println("安全的原子类，atomicInteger="+myData1.atomicInteger);//期望值是20000

    }
}
