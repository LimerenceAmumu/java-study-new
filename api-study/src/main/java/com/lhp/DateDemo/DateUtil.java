package com.lhp.DateDemo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Amumu
 * @create 2020/7/6 15:08
 */
public class DateUtil {
    @Test
    public void testDate() throws ParseException {
        String dateStr = "20201201";
        StringBuilder stringBuilder = new StringBuilder();
        //转换为日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dateStr);

        //日期运算
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, -1);
        Date yesDay = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        System.out.println("calendar = " + calendar);

    }

}
