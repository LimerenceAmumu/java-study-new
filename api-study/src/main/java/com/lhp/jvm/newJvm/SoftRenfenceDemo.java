package com.lhp.jvm.newJvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashSet;

public class SoftRenfenceDemo {

    public static void main(String[] args) {
        showInitialMemoryInfo();

        softReference();//演示软引用
//        softReferenceOverHeadLimit();//软引用溢出
    }

    /**
     * 演示软引用  -Xmx100m
     * 在这个方法中，先创建了一个只有软引用的对象，然后打印了该对象的地址。
     * 然后又创建了一个强引用对象，由于此时内存不足以创建新的对象，因此会回收之前创建的只有软引用的对象。
     * 然后再次创建强引用对象，由于此时已经无法分配足够的空间来创建对象，因此会抛出OOM的异常。
     */
    private static void softReference() {
        printSplitLine();

        //创建软引用对象
        SoftReference<SoftObject> reference = new SoftReference<>(new SoftObject());
        System.out.println(getCurrentMemoryInfo() + ",当前软引用对象:" + reference.get());

        printSplitLine();

        //创建强引用对象
        SoftObject object = new SoftObject();
        printSplitLine();
        System.out.println(getCurrentMemoryInfo() + ",当前软引用对象:" + reference.get());

        //创建强引用对象
        SoftObject object2 = new SoftObject();
        printSplitLine();
        System.out.println(getCurrentMemoryInfo() + ",当前软引用对象:" + reference.get());
    }

    /**
     * 演示软引用溢出  -Xmx40m
     * java.lang.OutOfMemoryError: GC overhead limit exceeded 抛出这个异常是因为，默认情况下，如果GC花费的时间超过98%,并且GC回收的内存少于2%，JVM就会抛出这个异常。
     */
    private static void softReferenceOverHeadLimit() {
        int capacity = 1024 * 1024;
        HashSet<SoftReference<SmallSoftObject>> set = new HashSet<>(capacity);
        for (int i = 0; i < capacity; i++) {
            set.add(new SoftReference<>(new SmallSoftObject()));
        }
        System.out.println("End");
    }

    /**
     * 演示软引用溢出解决方案
     */
    private static void softReferenceOverHeadLimitResolve() {
        int capacity = 1024 * 1024;
        HashSet<SoftReference<SmallSoftObject>> set = new HashSet<>(capacity);
        ReferenceQueue<SmallSoftObject> referenceQueue = new ReferenceQueue<>();
        for (int i = 0; i < capacity; i++) {
            set.add(new SoftReference<>(new SmallSoftObject(), referenceQueue));
            removeObject(set, referenceQueue);
        }
        System.out.println("End");
    }

    private static void removeObject(HashSet<SoftReference<SmallSoftObject>> set, ReferenceQueue<SmallSoftObject> referenceQueue) {
        Reference<? extends SmallSoftObject> poll = referenceQueue.poll();
        while (poll != null) {
            set.remove(poll);
            poll = referenceQueue.poll();
        }
    }

    /**
     * 显示初始的内存信息
     */
    private static void showInitialMemoryInfo() {
        MemoryMXBean mbean = ManagementFactory.getMemoryMXBean();
        System.out.println("最大可用内存:" + toMB(mbean.getHeapMemoryUsage().getMax()));
        for (MemoryPoolMXBean mxBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println("Name:" + mxBean.getName()
                    + ",Type:" + mxBean.getType()
                    + ",Size:" + toMB(mxBean.getUsage().getMax()));
        }
    }

    /**
     * 获取当前内存信息
     *
     * @return
     */
    private static String getCurrentMemoryInfo() {
        return "当前最大可用内存:" + toMB(Runtime.getRuntime().maxMemory()) + ",当前空闲内存:" + toMB(Runtime.getRuntime().freeMemory());
    }

    /**
     * 将字节转换成MB的格式
     *
     * @param b
     * @return
     */
    private static String toMB(Long b) {
        return String.format("%.2f M", (double) b / (1024 * 1024));
    }

    /**
     * 打印分隔行
     */
    private static void printSplitLine() {
        System.out.println("========================================================================");
    }

    static class SoftObject {
        byte[] data = new byte[50 * 1024 * 1024];
    }

    static class SmallSoftObject {
        byte[] data = new byte[1024];
    }
}