package com.lhp.collections;

import org.junit.Test;

import java.util.*;

/**
 * @author Amumu
 * @create 2019/10/24 15:37
 * 不要在 foreach 循环里进行元素的 remove/add 操作
 */
public class ForEach {
    @Test
    public void testJava8ForEach(){
        List<String> strings = Arrays.asList("A", "B", "C");
        strings.forEach(s-> System.out.println("s = " + s));
        HashMap<Object, Object> map = new HashMap<>();
        map.put("A","1");
        map.put("B","2");
        map.put("C","3");
        map.forEach((k,v)-> System.out.println(k+":"+v));
        Queue<String> namesQueue = new ArrayDeque<>(Arrays.asList("Larry", "Steve", "James"));

        namesQueue.forEach(System.out::println);
        Set<String> uniqueNames = new HashSet<>(Arrays.asList("Larry", "Steve", "James"));

        uniqueNames.forEach(System.out::println);

    }
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>(3);
        strings.add("A");
        strings.add("B");
        strings.add("C");
      /*  for (String string : strings) {
            if(Objects.equals("A",string)){
                strings.remove(string);
                *//**
                 * 如果要进行remove操作，可以调用迭代器的 remove 方法而不是集合类的 remove 方法。
                 * 因为如果列表在任何时间从结构上修改创建迭代器之后，以任何方式除非通过迭代器自身remove/add方法，
                 * 迭代器都将抛出一个ConcurrentModificationException,这就是单线程状态下产生的 fail-fast 机制。
                 *//*
            }
        }*/

        //正确的示范
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(Objects.equals(next,"A")){
                iterator.remove();
            }
        }

        System.out.println(Arrays.toString(strings.toArray(new String[0])));

    }
}
