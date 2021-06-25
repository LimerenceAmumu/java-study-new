package com.lhp.DateDemo;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

/**
 * @author Amumu
 * @create 2019/12/18 10:20
 */
public class InstantTest {
    @Test
    public void testInstant(){
        //直接获取和北京相差8个时区
        Instant now = Instant.now();
        System.out.println("now = " + now);
        //获取10位秒数 和13位毫秒数
        System.out.println("秒数:"+now.getEpochSecond());
        System.out.println("毫秒数:"+now.toEpochMilli());
        //增加8个时区
        Instant beijing = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("beijing = " + beijing);
        //获取当前时区的时间
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()));


    }
}
