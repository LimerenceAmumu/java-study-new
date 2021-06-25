package com.lhp.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Amumu
 * @create 2019/7/13 22:08
 * 查找和替换
 * int binarySearch(List list, Object key)//对List进行二分查找，返回索引，注意List必须是有序的
 * int max(Collection coll)//根据元素的自然顺序，返回最大的元素。 类比int min(Collection coll)
 * int max(Collection coll, Comparator c)//根据定制排序，返回最大元素，排序规则由Comparatator类控制。
 *      类比int min(Collection coll, Comparator c)
 * void fill(List list, Object obj)//用指定的元素代替指定list中的所有元素。
 * int frequency(Collection c, Object o)//统计元素出现次数
 * int indexOfSubList(List list, List target)//统计target在list中第一次出现的索引，找不到则返回-1，
 *      类比int lastIndexOfSubList(List source, list target).
 * boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素
 */
public class CollectionSearch {
    @Test
    public void test(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        arrayList2.add(-3);
        arrayList2.add(-5);
        arrayList2.add(7);
        System.out.println("原始数组:");
        System.out.println(arrayList);

        System.out.println("Collections.max(arrayList):");
        System.out.println(Collections.max(arrayList));

        System.out.println("Collections.min(arrayList):");
        System.out.println(Collections.min(arrayList));

        System.out.println("Collections.replaceAll(arrayList, 3, -3):");
        Collections.replaceAll(arrayList, 3, -3);
        System.out.println(arrayList);

        System.out.println("Collections.frequency(arrayList, -3):");
        System.out.println(Collections.frequency(arrayList, -3));

        System.out.println("Collections.indexOfSubList(arrayList, arrayList2):");
        System.out.println(Collections.indexOfSubList(arrayList, arrayList2));

        System.out.println("Collections.binarySearch(arrayList, 7):");
        // 对List进行二分查找，返回索引，List必须是有序的
        Collections.sort(arrayList);
        System.out.println(Collections.binarySearch(arrayList, 7));
    }

}
