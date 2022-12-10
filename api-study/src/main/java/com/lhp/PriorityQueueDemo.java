package com.lhp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/7/26 11:42
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparing(a -> a));
        priorityQueue.add(6);
        priorityQueue.add(5);
        priorityQueue.add(10);
        priorityQueue.add(2);

        System.out.println(priorityQueue.poll());


    }


    @Test
    public void test() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("asdas");
        strings.add("asdas");
        strings.add("asdas");
        strings.add("asdas");
        strings.forEach(System.out::println);
    }

}
