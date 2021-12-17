package com.lhp.map;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author lihp
 * @date 2021年11月30日 11:20
 */
public class TestClient {
    /**
     *map 的get方法是  get(Object key)
     */
    @Test
    public void test() {

        HashMap<String, String> dm = new HashMap<>();
        dm.put("1","5");
        String s = dm.get(1);
    }
}
