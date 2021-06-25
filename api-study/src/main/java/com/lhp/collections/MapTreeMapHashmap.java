package com.lhp.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Amumu
 * @create 2019/12/10 15:52
 */
public class MapTreeMapHashmap {
    public static void main(String[] args) {

        //证明hashmap的无序性，key不可重复
        testHashMap();


    }
    @Test
    public void testTreeMap(){
        //TreeMap 排序用默认升序 不可重复
        TreeMap<String, String> treeHashMap = new TreeMap<>();
        treeHashMap.put("A","A");
        treeHashMap.put("A","B");
        treeHashMap.put("A","B");
        treeHashMap.put("B","B");
        treeHashMap.put("D","C");
        treeHashMap.put("E","C");
        treeHashMap.put("F","C");
        treeHashMap.put("G","C");
        treeHashMap.put("a","C");
        treeHashMap.put("b","C");
        treeHashMap.put("c","C");
        treeHashMap.put("d","C");
        treeHashMap.put("e","C");
        treeHashMap.put("Z","C");

        System.out.println("treeHashMap = " + treeHashMap);
        //可以自定义排序规则
    }

    /**
     * 证明hashmap的无序性，key不可重复
     */
    public static void testHashMap() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("A","A");
        hashMap.put("A","B");
        hashMap.put("A","B");
        hashMap.put("B","B");
        hashMap.put("D","C");
        hashMap.put("E","C");
        hashMap.put("F","C");
        hashMap.put("G","C");
        hashMap.put("a","C");
        hashMap.put("b","C");
        hashMap.put("c","C");
        hashMap.put("d","C");
        hashMap.put("e","C");

        System.out.println(hashMap);
    }
}
