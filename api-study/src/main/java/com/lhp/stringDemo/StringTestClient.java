package com.lhp.stringDemo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2021/3/14 17:16
 */
public class StringTestClient {

    /**
     *
      */
    @Test
        public void testLength(){

        String ss="https://standard-new-data-govern-pifow.aihuoshi.net/";
        //https://standard-new-data-govern-pifow.aihuoshi.net/piflow-web/jwtLogin
        //https://standard-new-data-govern-pifow-api.aihuoshi.net/piflow-web/jwtLogin
        System.out.println(ss.length());
        }

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
     * 补齐
      */
    @Test
        public void testFill(){
        String s = StringUtils.leftPad("33", 5, "0");
        System.out.println("s = " + s);//00033
    }
    /**
     * String 最大长度
     */
    @Test
    public void testStringLength() {
        String collect = Stream.generate(() -> "s")
                .limit(Integer.MAX_VALUE)
                .collect(Collectors.joining());
        System.out.println("collect = " + collect.length());
    }

    @Test
    public void testSub() {
        String tableName = "public.test_lhp";
        String substring = tableName.substring(tableName.indexOf(".") + 1);
        System.out.println("substring = " + substring);


        String tableName2 = "test_lhp";
        String substring2 = tableName2.substring(tableName2.indexOf(".") + 1);
        System.out.println("substring = " + substring2);


        String testss = "206.5 kb";
        String testssRe = testss.substring(0, testss.indexOf(" "));
        System.out.println("testssRe = " + testssRe);

    }

    @Test
    public void testDiv() {

        int a = 15;
        int b = 36;
        System.out.println(a / b);
    }


    @Test
    public void testNumtoStr() {
        int a = 15;

        String ss = a + "";
        System.out.println("ss = " + ss);
    }

}
