package com.lhp.hutool;

import com.xiaoleilu.hutool.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/2/10 15:18
 */
public class DateDemo {

    public static void main(String[] args) {

        //Date、long、Calendar之间的相互转换
        //当前时间
        Date date = DateUtil.date();
        //Calendar转Date
        date = DateUtil.date(Calendar.getInstance());
        //时间戳转Date
        date = DateUtil.date(System.currentTimeMillis());
        //自动识别格式转换
        String dateStr = "2017-03-01";
        date = DateUtil.parse(dateStr);
        //自定义格式化转换
        date = DateUtil.parse(dateStr, "yyyy-MM-dd");
        //格式化输出日期
        String format = DateUtil.format(date, "yyyy-MM-dd");
        //获得年的部分
        int year = DateUtil.year(date);
        //获得月份，从0开始计数
        int month = DateUtil.month(date);
        //获取某天的开始、结束时间
        Date beginOfDay = DateUtil.beginOfDay(date);
        Date endOfDay = DateUtil.endOfDay(date);

    }
}
