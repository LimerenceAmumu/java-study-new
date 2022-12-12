package com.lhp.map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author lihp
 * @date 2021年11月30日 11:20
 */
public class TestClient {
    /**
     * map 的get方法是  get(Object key)
     */
    @Test
    public void test() {

        HashMap<String, String> dm = new HashMap<>();
        dm.put("1", "5");
        String s = dm.get(1);
    }


    @Test
    public void test22() {
        HashMap<String, Integer> hsTest = new HashMap<String, Integer>();
        for (int i = 0; i < 13; i++) {
            hsTest.put(i + "", i);
        }
        hsTest.get(0);
        System.out.println("the size of hsTest is " + hsTest.size());
    }


    @Test
    public void test2() {
        // 1. 双键测试
        Table<String, String, String> table = HashBasedTable.create();
        table.put("1", "a", "第1组第a个数据");
        table.put("1", "b", "第1组第b个数据");
        table.put("2", "a", "第2组第a个数据");
        table.put("2", "b", "第2组第b个数据");
        System.out.println(table.get("1", "a"));
        System.out.println(table.get("1", "b"));
        System.out.println(table.get("2", "a"));
        System.out.println(table.get("2", "b"));
    }

    @Test
    public void testss() {

    }


}
