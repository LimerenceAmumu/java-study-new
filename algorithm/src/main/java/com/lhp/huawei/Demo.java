package com.lhp.huawei;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Description: ces
 * @author: lihp
 * @date: 2021/8/30 3:59 下午
 */
public class Demo {
    public static void main(String[] args) {

    }

    /**
     * 获取下个版本号
     *
     * @param curVersion
     * @return
     */
    static String getNestVersion(String curVersion) {
        String oldVersionNumStr = curVersion.substring(1);
        BigDecimal oldVersionNum = new BigDecimal(oldVersionNumStr);
        BigDecimal newVersionNum = oldVersionNum.add(new BigDecimal("0.1")).setScale(1, BigDecimal.ROUND_UP);

        return "v" + newVersionNum;
    }

    @Test
    public void testDD() {
        String nestVersion = getNestVersion("v2.06");
        System.out.println("nestVersion = " + nestVersion);
    }
}
