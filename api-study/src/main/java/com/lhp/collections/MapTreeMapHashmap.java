package com.lhp.collections;

import org.junit.Test;

import java.util.TreeMap;

/**
 * @author Amumu
 * @create 2019/12/10 15:52
 */
public class MapTreeMapHashmap {
    public static void main(String[] args) {




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
     * 自己的hashmap
     */
    @Test
    public void testHashMap() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();

        stringStringHashMap.put("s", "ss");
        String s = stringStringHashMap.get("s");
        System.out.println("s = " + s);

    }
}
