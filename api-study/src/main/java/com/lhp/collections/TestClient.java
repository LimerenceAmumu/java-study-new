package com.lhp.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author lihp
 * @date 2021年11月29日 16:36
 */
public class TestClient {
    /**
     *
     */
    @Test
    public void testEmptyList() {
        List<String> allData = Collections.emptyList();
        List<String> dbData = Collections.emptyList();
        System.out.println(allData==dbData);
        boolean asdas = dbData.add("asdas");
        System.out.println("asdas = " + asdas);
    }
}
