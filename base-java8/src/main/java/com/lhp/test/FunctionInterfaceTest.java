package com.lhp.test;

import com.lhp.bean.Apple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Amumu
 * @create 2020/9/2 15:17
 * 函数式接口
 * predicate  打印筛选过后的苹果
 * consumer   打印苹果重量
 * function   获取苹果归属地列表 List<Apple> -> List<String>
 */
public class FunctionInterfaceTest {
    public static void main(String[] args) {

        init();
/*        filter(apples, (a) -> {
            return "red".equals(a.getColor());
        });*/

/*
        consumer(apples,(a)-> System.out.println("a.getWeight() = " + a.getWeight()));
*/

        List<Integer> function = function(apples, apple ->
                apple.getWeight()
        );
        function.forEach(System.out::println);

    }

    public static <T, R> List<R> function(List<T> datas, Function<T, R> function) {
        ArrayList<R> rs = new ArrayList<>();
        for (T data : datas) {
            rs.add(function.apply(data));
        }
        return rs;
    }

    public static <T> void consumer(List<T> datas, Consumer<T> consumer) {

//        consumer.accept();
        for (T data : datas) {
            consumer.accept(data);
        }
    }

    //一个过滤apple方法
    //1筛选出红
    public static <T> void filter(List<T> datas, Predicate<T> predicate) {
        for (T t : datas) {

            if (predicate.test(t)) {
                System.out.println("apple = " + t);
            }
        }
    }

    static List<Apple> apples = new ArrayList();

    public static void init() {

        apples.add(new Apple("red", 150, "hn"));
        apples.add(new Apple("red", 150, "zz"));
        apples.add(new Apple("yellow", 80, "sd"));
        apples.add(new Apple("black", 150, "hh"));
        apples.add(new Apple("green", 200, "hd"));
        apples.add(new Apple("orign", 110, "tf"));
        apples.add(new Apple("red", 150, "hn"));
        apples.add(new Apple("red", 60, "hn"));

    }
}
