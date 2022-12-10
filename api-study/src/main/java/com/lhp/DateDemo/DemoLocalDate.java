package com.lhp.DateDemo;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * @author Amumu
 * @create 2019/11/4 21:10
 */
public class DemoLocalDate {
    public static void main(String[] args) {
        //获取当前 的日期
        LocalDate localDate = LocalDate.now();
        //当前日期 = 2019-12-18
        System.out.println("当前日期 = " + localDate);
        //任意构造一个日期

        LocalDate dateRan = LocalDate.of(2033, 12, 30);
        //自定义的日期 = 2033-12-30
        System.out.println("自定义的日期 = " + dateRan);
        //获取年月日 星期几
        System.out.println("年："+localDate.getYear());
        int year = localDate.get(ChronoField.YEAR);
        System.out.println("年：" + year);

        Month month1 = localDate.getMonth();
        System.out.println("月 = " + month1);
        int i1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("月 = " + i1);
        int i2 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("月 日 = " + i2);
        int i3 = localDate.get(ChronoField.DAY_OF_YEAR);
        System.out.println("年 日 = " + i3);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("星期 = " + dayOfWeek);
        int i4 = localDate.get(ChronoField.DAY_OF_WEEK);
        System.out.println("星期= " + i4);

        //时间
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间 = " + localTime);
        System.out.println(localTime.get(ChronoField.HOUR_OF_DAY));
        System.out.println(localTime.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println(localTime.get(ChronoField.SECOND_OF_MINUTE));



        //获取当前的日期+时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        //创建LocalDateTime
        LocalDateTime of = LocalDateTime.of(2011, 12, 20, 22, 22, 59);
        LocalDateTime of1 = LocalDateTime.of(2011, Month.JANUARY, 20, 22, 22, 59);
        LocalDateTime of2= LocalDateTime.of(localDate,localTime);
        LocalDateTime localDateTime = localDate.atTime(localTime);
        LocalDateTime localDateTime1 = localTime.atDate(localDate);

        //修改相关操作
        //修改为2010
        LocalDate localDate1 = localDate.withYear(2010);
        LocalDate with = localDate.with(ChronoField.MONTH_OF_YEAR, 2);

        LocalDate localDate2 = localDate.plusDays(5);//天数+5
        LocalDate localDate3 = localDate.minusMonths(1);//月份-1

        int i = localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH);
        System.out.println("ALIGNED_DAY_OF_WEEK_IN_MONTH = " + i);
        int i5 = localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        System.out.println("i5 = " + i5);


        //


    }

    @Test
    public void test() {
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(1648015317741L, 0, ZoneOffset.ofHours(8));
        LocalDateTime localDateTiaame = new Date(1648015317741L).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();

        System.out.println("localDateTime = " + localDateTime);
        System.out.println("localDateTiaame = " + localDateTiaame);
    }


    @Test
    public void test03() {

        //获取秒数
        Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println("second = " + second);
//获取毫秒数
        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println("milliSecond = " + milliSecond);
    }

}
