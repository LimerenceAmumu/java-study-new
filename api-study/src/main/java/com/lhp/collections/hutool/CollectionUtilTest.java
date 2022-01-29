package com.lhp.collections.hutool;

import com.xiaoleilu.hutool.util.CollectionUtil;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/10/10 9:57 上午
 */
public class CollectionUtilTest {

    @Test
    public void testSub() {
        List<String> collect = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());

        // start： 包含  end 不包含 ，可以尽量把end写大
        List<String> sub = CollectionUtil.sub(collect, 3, 7);
        sub.forEach(System.out::println);
    }

}
