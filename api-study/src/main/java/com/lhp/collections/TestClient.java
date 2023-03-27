package com.lhp.collections;

import com.lhp.bean.Apple;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lihp
 * @date 2021年11月29日 16:36
 */
@Slf4j
public class TestClient {
    /**
     *
     */
    @Test
    public void testEmptyList() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ddd");
        CopyOnWriteArrayList<String> strings1 = new CopyOnWriteArrayList<>();

        List<Integer> integers = Collections.synchronizedList(new ArrayList<Integer>());
        LinkedList<String> strings2 = new LinkedList<>();
    }


    @Test
    public void test2() {

        ArrayList<Apple> apples = new ArrayList<>();
        Apple apple = new Apple();
        apple.setColor("sss");
        apple.setWeight(20);

        apples.add(apple);

        apple.setWeight(40);

        Apple apple2 = new Apple();
        apple2.setColor("2222");
        apple2.setWeight(60);

        apple = apple2;

        log.info("apples.get(0).toString(): {}", apples.get(0));

        log.info("apple: {}", apple);
        log.info("apples.get(0).toString(): {}", apples.get(0));


    }

}
