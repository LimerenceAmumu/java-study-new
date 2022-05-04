package com.lhp.collections;

import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
        ArrayList<String> strings = new ArrayList<>();
        strings.add("ddd");
        CopyOnWriteArrayList<String> strings1 = new CopyOnWriteArrayList<>();

        List<Integer> integers = Collections.synchronizedList(new ArrayList<Integer>());
        LinkedList<String> strings2 = new LinkedList<>();
    }
}
