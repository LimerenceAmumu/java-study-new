package com.lhp.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/4/3 10:44
 */
public class CopyOnwriteErrorDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

        List<Object> objects = Collections.synchronizedList(new com.lhp.collections.ArrayList<>());
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
        }, "remore-t").start();

        new Thread(() -> {
            while (true) {
                if (integers.size() == 0) {
                    break;
                }
                integers.get(integers.size() - 1);

            }


        }, "get-t").start();

    }
}
