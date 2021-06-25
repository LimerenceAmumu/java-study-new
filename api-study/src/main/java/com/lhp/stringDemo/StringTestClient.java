package com.lhp.stringDemo;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2021/3/14 17:16
 */
public class StringTestClient {
    /**
     * 替换
     */
    @Test
    public void testFormat(){
        String ss="申请编码：%s, name: %s";
        String format = String.format(ss, "AA", "lihepeng");
        System.out.println("format = " + format);
    }
    /**
     * String 最大长度
     */
    @Test
    public void testStringLength(){
        String collect = Stream.generate(() -> "s")
                .limit(Integer.MAX_VALUE)
                .collect(Collectors.joining());
        System.out.println("collect = " + collect.length());
    }
}
