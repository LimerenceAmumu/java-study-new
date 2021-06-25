package com.lhp.DateDemo;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Amumu
 * @create 2019/7/12 18:15
 */
public class CalendarDemo {
    public static void main(String[] args) {
        // 创建Calendar对象
        Calendar cal = Calendar.getInstance();
        // 使用add方法
        cal.add(Calendar.DAY_OF_MONTH, 2); // 加2天
        cal.add(Calendar.YEAR, -3); // 减3年
        // 设置年
        cal.set(Calendar.YEAR,2020);
        // 获取年
        int year = cal.get(Calendar.YEAR);
        // 获取月 （从0开始，可以+1使用）
        int month = cal.get(Calendar.MONTH) + 1;
        // 获取日
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        Date date = cal.getTime();
        System.out.println("date = " + date);
        System.out.print(year + "年" + month + "月" + dayOfMonth + "日");
    }
}
