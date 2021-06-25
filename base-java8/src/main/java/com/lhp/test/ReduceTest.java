package com.lhp.test;

import org.junit.Test;

import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author Amumu
 * @create 2020/6/27 11:05
 * 归约  终端操作
 * 有状态操作
     * 排序要求所有元素都放入缓冲区
     * 后才能给输出流加入一个项目， 这一操作的存储要求是无界的。 要是流比较大
     * 或是无限的， 就可能会有问题（ 把质数流倒序会做什么呢？ 它应当返回最大的
     * 质数， 但数学告诉我们它不存在） 。 我们把这些操作叫作有状态操作。
 */
public class ReduceTest {
    @Test
    public void testAdd(){
        IntStream intStream = IntStream.rangeClosed(1, 10);
        // 1到10的阶乘
        // 1 是初始值
        int reduce = intStream.reduce(1, (n1, n2) -> {
            return n1 * n2;
        });
        IntStream intStream2 = IntStream.rangeClosed(1, 10);
        System.out.println("reduce = " + reduce);
        //还可以这样
        OptionalInt reduce1 = intStream2.reduce((n1, n2) -> {
            return n1 * n2;
        });
        int i = reduce1.orElse(1);
        System.out.println("i = " + i);


        // 求和
        IntStream.rangeClosed(1,100).reduce(0,Integer::sum);

    }
}
