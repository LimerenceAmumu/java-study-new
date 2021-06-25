package com.lhp.collections;

/**
 * @author Amumu
 * @create 2019/10/24 16:01
 */
public class MinValue {

    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) {
                //Min 大于当前值
                min = values[i];
            }
        }
        return min;
    }
}
