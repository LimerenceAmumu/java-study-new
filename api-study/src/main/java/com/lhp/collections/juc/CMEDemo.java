package com.lhp.collections.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Amumu
 * @create 2020/8/27 8:55
 * 并发修改异常
 */
public class CMEDemo {

    public static void main(String[] args) {
        //ArrayList<String> listDemo = new ArrayList<>();//保错

        // 1  使用Collections.synchronizedList
        List<String> listDemo = Collections.synchronizedList(new ArrayList<String>());
        currentAdd(listDemo);
    }

    // 错误示范
    public static void currentAdd(List strings ) {
        for(int i = 0; i < 30; i++) {
            new Thread(()->{
                strings.add(UUID.randomUUID().toString());
                strings.forEach(System.out::println);
            },"Thread:"+i).start();
            //ConcurrentModificationException
        }
    }
}
