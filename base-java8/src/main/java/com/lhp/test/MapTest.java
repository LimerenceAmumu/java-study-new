package com.lhp.test;

import com.lhp.bean.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2020/6/27 11:52
 * java8
 */
public class MapTest {
    List<Dish> menu=new ArrayList<Dish>();
    @Before
    @Test
    public void testInitData(){
        menu.add(new Dish("红烧肉",500,30d));
        menu.add(new Dish("红烧茄子",500,30d));
        menu.add(new Dish("回锅肉",600,60d));
        menu.add(new Dish("番茄炒蛋",900,35d));
        menu.add(new Dish("辣子鸡",500,33d));
        //menu.add(new Dish(null,null,33d));
    }
    //转换为数值流
    @Test
    public void testMapToInt(){
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
    }
    //转换回对象流
    @Test
    public void testIntStramToObjStream(){
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> objectStream = intStream.boxed();

    }
}
