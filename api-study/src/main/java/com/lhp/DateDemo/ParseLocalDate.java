package com.lhp.DateDemo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Amumu
 * @create 2019/11/4 22:18
 *
 */
public class ParseLocalDate {
    public static void main(String[] args) {

        //格式化
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("DateTimeFormatter.BASIC_ISO_DATE = " + format);

        String format1 = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE_TIME = " + format1);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM==dd hh--mm--SS");
        String format2 = now.format(dateTimeFormatter);
        System.out.println("yyyy/MM==dd hh--mm--SS = " + format2);

        //解析
 /*       LocalDateTime parse = LocalDateTime.parse(format,DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("parse = " + parse);*/
        LocalDateTime parse1 = LocalDateTime.parse(format1,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("parse1 = " + parse1);
    }
}
