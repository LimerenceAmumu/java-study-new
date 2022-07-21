package com.lhp.thread;

import java.util.concurrent.atomic.LongAdder;

/**
 * @auther zzyy
 * @create 2022-02-26 18:51
 */
public class LongAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.sum());
    }
}