package com.lhp.test;

import com.lhp.bean.Trader;
import com.lhp.bean.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2020/6/20 11:00
 */
public class StreamTest {
    List<Transaction> transactions = null;

    @Before
    @Test
    public void testInitData() {
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
     * (1) 找出2011年发生的所有交易， 并按交易额排序（ 从低到高） 。
     * (2) 交易员都在哪些不同的城市工作过？
     * (3) 查找所有来自于剑桥的交易员， 并按姓名排序。
     * (4) 返回所有交易员的姓名字符串， 按字母顺序排序。
     * (5) 有没有交易员是在米兰工作的？
     * (6) 打印生活在剑桥的交易员的所有交易额。
     * (7) 所有交易中， 最高的交易额是多少？
     * (8) 找到交易额最小的交易
     */
    @Test
    public void testStream() {
        // (1) 找出2011年发生的所有交易， 并按交易额排序（ 从低到高） 。
        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("collect = " + collect);
        //(2) 交易员都在哪些不同的城市工作过？
        List<String> citys = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("citys = " + citys);

        //(3) 查找所有来自于剑桥的交易员， 并按姓名排序。
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("traders = " + traders);
        //(4) 返回所有交易员的姓名字符串， 按字母顺序排序。
        String names = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .reduce("-", (n1, n2) -> n1 + "==" + n2);
        //* (5) 有没有交易员是在米兰工作的？
        boolean hasMilan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        //* (6) 打印生活在剑桥的交易员的所有交易额。
        Set<Integer> cambridgeValue = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toSet());

        //* (7) 所有交易中， 最高的交易额是多少？
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        //* (8) 找到交易额最小的交易
        Optional<Transaction> minTramsaction = transactions.stream()
                .reduce((t1, t2) -> {
                            return t1.getValue() > t2.getValue() ? t2 : t1;
                        }
                );

        Optional<Transaction> reduce = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("reduce = " + reduce);

    }

    /**
     * 由值创建流
     * 由数组创建流
     * 由函数生成流（无限流）
     * Stream.iterate
     */
    @Test
    public void testCreateStream(){
        Stream<Integer> integerStream = Stream.of(1, 2, 5);
        Integer[] ints={6,556,95};
        Stream<Integer> stream = Arrays.stream(ints);
        //由函数生成流（无限流）
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        //Supplier<T>  可以是有状态的
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    /***
     * 使用无限流 生成斐波那契
     */
    @Test
    public void testFibonacci(){
        // type1
        Stream.iterate(new int[]{0,1},(f)->
            new int[]{f[1],f[0]+f[1]}
        )
                .map(f->f[1])
                .limit(20)
                .forEach(System.out::println);
        //type2  错误示范 不适用于 并行
        /**
         * 此对象有可变的状态： 它在两个实例
         * 变量中记录了前一个斐波纳契项和当前的斐波纳契项。 getAsInt在调用时会改变对
         * 象的状态， 由此在每次调用时产生新的值。 相比之下， 使用iterate的方法则是纯
         * 粹不变的： 它没有修改现有状态， 但在每次迭代时会创建新的元组。
         */
        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }
    @Test
    public void test3() {
        Stream<int[]> ints = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        ints.forEach(i -> System.out.println(Arrays.toString(i)));
        //优化
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}
                        ).filter(i -> i[2] % 1 == 0));
    }
}
