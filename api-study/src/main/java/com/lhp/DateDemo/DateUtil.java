package com.lhp.DateDemo;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Amumu
 * @create 2020/7/6 15:08
 */
public class DateUtil {

    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String HHMMSS = "HH:mm:ss";
    public static final String YYYYMMDD_HHMMSS2 = "yyyy/MM/dd HH:mm:ss";
    public static final String YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDD2 = "yyyy/MM/dd";
    public static final String YYYYMM = "yyyy-MM";
    public static final String MMDD = "MM/dd";
    public static final String YYYY = "yyyy";
    public static final String GMT8 = "GMT+8";
    public static final String MM = "MM";

    public static final DateTimeFormatter HHMMSS_dateTimeFormatter = DateTimeFormatter.ofPattern(HHMMSS);
    public static final DateTimeFormatter YYYYMMDD_dateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDD);
    public static final DateTimeFormatter YYYYMMDD_HHMMSS_dateTimeFormatter = DateTimeFormatter.ofPattern(YYYYMMDD_HHMMSS);


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

    /**
     * 获取近30天的日期list 字符
     */
    @Test
    public void testGet30DateStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i <= 29; i++) {
            LocalDate now = LocalDate.now();
            LocalDate localDate = now.minusDays(i);
            String nowStr = localDate.format(dateTimeFormatter);
            result.add(nowStr);
        }
        System.out.println("result = " + result);
    }


    /**
     * 计算日期间隔
     */
    @Test
    public void test33() {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime localDateTime = now.minusHours(25);
        LocalDateTime localDateTime2 = now.plusDays(25);


        long l = Duration.between(now, localDateTime).toDays();
        System.out.println("l = " + l);

        long l2 = Duration.between(now, localDateTime2).toDays();
        System.out.println("l2 = " + l2);
    }


}
