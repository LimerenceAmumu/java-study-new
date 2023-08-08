package com.lhp.collections;

import com.lhp.bean.Apple;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/8/8 16:20
 */
public class PriorityQueueTest {

    ArrayList<Apple> apples = new ArrayList<>(10);

    private static int compare(Apple a1, Apple a2) {
        if (Objects.equals(a1.getColor(), a2.getColor()))
            return a1.getWeight().compareTo(a2.getWeight());
        return a1.getColor().compareTo(a2.getColor());
    }

    @Before
    public void before() {
//        apples.
    }

    @Test
    public void test1() {

        PriorityQueue<Apple> apples = new PriorityQueue<>(10, PriorityQueueTest::compare);
//        apples.offer()
    }

}
