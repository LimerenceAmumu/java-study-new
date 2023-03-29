package com.lhp.thread.lock;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Amumu
 * @create 2019/9/24 16:47
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    //停了两秒
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "释放车位");


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();

                }
            }, i + "==").start();
        }

    }


    @Test
    public void test() {

        String input = "[126]-[336]-dasd5";
        String pattern = "\\[([^\\[\\]]*)\\].*?\\[([^\\[\\]]*)\\]";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);

        if (m.find()) {
            System.out.println(m.group(2)); // 输出结果为 336
        } else {
            System.out.println("未找到匹配项");
        }
    }

}
