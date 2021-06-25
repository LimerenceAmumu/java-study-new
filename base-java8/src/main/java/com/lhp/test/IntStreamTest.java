package com.lhp.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2020/6/27 14:37
 * <p>
 * 数值流
 */
public class IntStreamTest {
    @Test
    public void testCount() {
        /**
         * 1-100 de ou shu
         * rangeClosed方法来生成1到100之间的所有数字
         * public static IntStream rangeClosed
         */
        long count = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .count();

    }

    /**
     * 先来个总结：
     *
     * map是对一级元素进行操作，flatmap是对二级元素操作。
     *
     * map自动返回stream对象，flatmap处理后的元素依然要是stream对象（可以用stream.of，Arrays.stream将元素转为stream对象）。
     *
     * 打个比方，有一个二维数组a[2][3]，通过map每次操作的都是一个一维数组，如a[0]和a[1]，执行完后就各自返回一个处理后的一维数组,这样a[2][3]经过map操作后就会返回两个一维的数组，然后汇集成一个stream后返回。
     *
     * 而flatmap每次操作后就是单个元素，比如a[0][0],a[0][1],所有元素执行完后再汇集起来变成一个stream。
     * ————————————————
     */
    @Test
    public void testPythagoreanNum() {
        //基础的 数值流
        IntStream intStream = IntStream.rangeClosed(1, 100);
        //转为对象流
        Stream<Integer> boxed = intStream.boxed();
        //
        boxed.flatMap(a ->
                IntStream.rangeClosed(a, 100)
                        .mapToObj(b ->
                                new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(p -> p[2] % 1 == 0))
                .forEach(a -> System.out.println(Arrays.toString(a)));

    }

    @Test
    public void testMapAndFlatMap() {
        String[] ss = {
                "hello welcome",
                "world hello",
                "hello world",
                "hello world welcome"};
        Stream<String> stringStream = Stream.of(ss);
        stringStream.map(str -> Arrays.stream(str.split(" ")))
                .forEach(s -> System.out.println(Arrays.toString(s.toArray())));

        Stream<String> ss2 = Stream.of(ss);
        //flatMap
        ss2.flatMap(str -> Arrays.stream(str.split(" ")))
                .forEach(System.out::println);

    }
}
