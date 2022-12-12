package com.lhp.thread.base;

import com.lhp.thread.runnable.LongTimeTask;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/5/5 17:15
 */
public class BaseMethodDemo {
    public static void main(String[] args) {
        Thread thread = new DemoThread();
        thread.setDaemon(true);
        thread.start();


        Thread runableThread = new Thread(new LongTimeTask(), "runableThread");
        runableThread.start();
    }
}
