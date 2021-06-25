package com.lhp.DateDemo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Amumu
 * @create 2019/7/12 17:55
 *
 */
public class DateDemo {
    public static void main(String[] args) throws ParseException {

        showDays();

    }
    //计算一个人出生了多少天
    public static void showDays() throws ParseException {
        //1.获取出生日期
        String dateStr = new Scanner(System.in).next();
        //2.格式化为date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        //3.当前日期
        Date today = new Date();
        System.out.println((today.getTime()-date.getTime())/1000/60/60/24);
    }



}
