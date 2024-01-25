package com.lhp.bignum;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description: BigDecimal api测试
 * @author: lihp
 * @date: 2021/10/12 13:59
 */
public class BigdeceNumTest {

    @Test
    public void test() {

        BigDecimal divide = new BigDecimal(1)
                .divide(new BigDecimal(6), 2, BigDecimal.ROUND_HALF_DOWN)
                .multiply(new BigDecimal(100));
        String s = divide.toString();
        System.out.println(s.substring(0, s.indexOf(".")));
        System.out.println("divide = " + divide);
    }

}
