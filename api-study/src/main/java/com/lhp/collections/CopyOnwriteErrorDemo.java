package com.lhp.collections;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author : lihp
 * @Description : CopyOnWriteArrayList  错误演示
 * 不是绝对线程安全   如果涉及到remove操作，一定要谨慎处理。
 * <p>
 * <p>
 * 1  2  3
 * 1  2
 * @date : 2023/4/3 10:44
 */
public class CopyOnwriteErrorDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

//        List<Object> objects = Collections.synchronizedList(new com.lhp.collections.ArrayList<>());
        ArrayList<Integer> integers1 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            integers1.add(i);
        }
        integers.addAll(integers1);


        new Thread(() -> {
            for (int i = 0; i < integers.size(); i++) {
                integers.remove(0);
                try {
                    TimeUnit.MICROSECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "remove-t").start();

        new Thread(() -> {
            while (true) {
                if (integers.size() == 0) {
                    break;
                }
                //integers.size() 这一步的时候还是正常的  真正 get 的时候数组大小改变了
                integers.get(integers.size() - 1);
            }
        }, "get-t").start();

    }
}
