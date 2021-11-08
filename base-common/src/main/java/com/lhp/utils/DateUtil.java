package com.lhp.utils;

import com.lhp.bean.DateEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/11/8 16:12
 */
public class DateUtil {
    public static void main(String[] args) {
        List<DateEntity> orders = new ArrayList<>();
        DateEntity order1 = new DateEntity();
        order1.setUpholdPerson("张三1");
        order1.setUpholdDate(LocalDate.now());
        DateEntity order2 = new DateEntity();
        order2.setUpholdPerson("张三2");
        order2.setUpholdDate(LocalDate.now().withMonth(9));
        DateEntity order3 = new DateEntity();
        order3.setUpholdPerson("张三3");
        order3.setUpholdDate(LocalDate.now());
        DateEntity order4 = new DateEntity();
        order4.setUpholdPerson("张三4");
        order4.setUpholdDate(LocalDate.now().withMonth(8));
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);

        //分组 按月
        Map<Integer, List<DateEntity>> groupByMonth = orders.stream().collect(Collectors.groupingBy(o -> o.getUpholdDate().getMonthValue()));
        System.out.println(groupByMonth);

    }
}