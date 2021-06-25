package com.lhp.collections;

import java.util.ArrayList;

/**
 * @author Amumu
 * @create 2019/7/17 11:26
 */
public class CopyTest {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>(2);
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
        System.arraycopy(arrayList,0,arrayList,0,3);
        System.out.println(arrayList);

    }
}
