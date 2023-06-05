package com.lhp.test;

import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.lhp.bean.Dish;
import com.lhp.bean.Trader;
import com.lhp.bean.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.*;


/**
 * @author Amumu
 * @create 2020/6/21 18:08
 * 第六章 用流收集数据
 */
public class CollectorClient {
    List<Transaction> transactions = null;

    List<Dish> menu = new ArrayList<Dish>();

    @Before
    @Test
    public void testInitData() {
        menu.add(new Dish("红烧肉", 500, 30d));
        menu.add(new Dish("红烧茄子", 500, 30d));
        menu.add(new Dish("回锅肉", 600, 60d));
        menu.add(new Dish("番茄炒蛋", 900, 35d));
        menu.add(new Dish("辣子鸡", 500, 33d));
        //menu.add(new Dish(null,null,33d));

        //交易员
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        //订单
        transactions = Arrays.asList(

                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));
    }

    /**
     * 按交易类型分组
     * groupingBy
     */
    @Test
    public void testGroupBy() {
        Trader raoul = new Trader("Raoul", "Cambridge");

        Supplier<Transaction> transactionSupplier = () -> new Transaction(raoul, 222, 20000);


        Map<Integer, List<Transaction>> groupByYear =
                transactions.stream().collect(groupingBy(Transaction::getYear));
        System.out.println("transactionsByCurrencies = " + groupByYear);

        Map<Integer, List<Transaction>> groupByValue = transactions
                .stream()
                .collect(groupingBy(Transaction::getValue));
        System.out.println("groupByValue = " + groupByValue);

        for (Map.Entry<Integer, List<Transaction>> integerListEntry : groupByYear.entrySet()) {

        }
    }

    @Test
    public void testYuanqing() {
        transactions.stream().collect(groupingBy(Transaction::getYear));

    }

    //获得热量最大的
    @Test
    public void testGetMaxCaloies() {
        //1
        Optional<Dish> first = menu.stream()
                .max(Comparator.comparing(Dish::getCalories));
        Dish dish = first.orElse(null);
        System.out.println("dish = " + dish);
        //2
        Comparator<Dish> maxbyComparator = Comparator.comparing(Dish::getCalories);
        Optional<Dish> collect = menu.stream().collect(maxBy(maxbyComparator));
    }


    //汇总  平均 最大最小
    @Test
    public void testSum() {
        Integer sum = menu.stream().collect(summingInt(Dish::getCalories));

        int totalCalories = menu.stream().collect(reducing(0,// ←─初始值
                Dish::getCalories,// ←─转换函数
                Integer::sum));
        Double aver = menu.stream().collect(averagingInt(Dish::getCalories));
        //all  可以通过get获得  min max aver  total  count
        //summarizingLong  summarizingDouble
        IntSummaryStatistics collect = menu.stream().collect(summarizingInt(Dish::getCalories));

        System.out.println(collect.toString());


        Optional<Dish> mostCalorieDish = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
    }

    //join
    @Test
    public void testJoin() {

        String collect = menu.stream()
                .filter(dish -> !Strings.isNullOrEmpty(dish.getName()))
                .map(Dish::getName)
                .collect(joining("-"));
        System.out.println("collect = " + collect);
    }
    //收集器在某种程度上比Stream接口上直接提供的方法用起来更复杂， 但好处在
    //于它们能提供更高水平的抽象和概括， 也更容易重用和自定义。
    /**
     * reduce方法旨在把两个值结合起来生成一个新值， 它是一个不可变的归
     * 约。 与此相反， collect方法的设计就是要改变容器， 从而累积要输出的结
     * 果。 
     */

}
