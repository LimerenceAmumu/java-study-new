package com.lhp.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author Amumu
 * @create 2019/7/13 21:47
 * 排序操作
 * void reverse(List list)//反转
 * void shuffle(List list)//随机排序
 * void sort(List list)//按自然排序的升序排序
 * void sort(List list, Comparator c)//定制排序，由Comparator控制排序逻辑
 * void swap(List list, int i , int j)//交换两个索引位置的元素
 * void rotate(List list, int distance)//旋转。当distance为正数时，将list后distance个元素整体移到前面。
 *          当distance为负数时，将 list的前distance个元素整体移到后面。
 */
public class CollectionSort {
    ArrayList<Integer> arrayList;
    @Before
    public void before (){

        arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
    }
    @Test
    public void reverse () {
        //反转
        System.out.println("原数组"+arrayList.toString());
        List<Integer> list=new LinkedList<>();
        list.get(1);

        Collections.reverse(arrayList);
        System.out.println("反转后的数组"+arrayList.toString());
    }
    @Test
    public void shuffle () {
        //反转
        System.out.println("原数组"+arrayList.toString());

        Collections.shuffle(arrayList);
        System.out.println("随机位置的数组"+arrayList.toString());
    }
    @Test
    public void sort () {
        //默认升序
        System.out.println("原数组"+arrayList.toString());

        Collections.sort(arrayList);
        System.out.println("升序数组"+arrayList.toString());
    }
    @Test
    public void sortPersonal () {
        ArrayList<Object> objects = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        //降序
        System.out.println("原数组"+arrayList.toString());
        Collections.sort(arrayList, Comparator.reverseOrder());

        //Collections.sort(arrayList,(a,b)->b.compareTo(a));
       /* Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
               return o2.compareTo(o1);

            }
        });*/
        System.out.println("降序数组"+arrayList.toString());
    }
    @Test
    public void rotate () {
        //反转void rotate(List list, int distance)
        // 旋转。当distance为正数时，将list后distance个元素整体移到前面。
        // 当distance为负数时，将 list的前distance个元素整体移到后面。
        System.out.println("原数组"+arrayList.toString());

        Collections.rotate(arrayList,3);
        System.out.println("反转后的数组"+arrayList.toString());
    }
    @Test
    public void swap () {
        //反转
        System.out.println("原数组"+arrayList.toString());

        Collections.swap(arrayList,0,1);
        System.out.println("交换后的数组"+arrayList.toString());
    }

    @Test
    public void test(){
        System.out.println(671464%32);
        System.out.println(671464&31);
    }
    @Test
    public void test1(){
        Integer a=2;
        Integer b=5;
        int i = a.compareTo(b);
        System.out.println("i = " + i);
    }


}
