package com.lhp.util;

import org.junit.Test;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/9/28 4:41 下午
 */
public class testClient {

    @Test
    public void test() {
        PageUtil pageUtil = new PageUtil(2, 30, 2);
        pageUtil.setPageSize(2);
        int startIndex = pageUtil.getStartIndex();
        System.out.println("startIndex = " + startIndex);
    }
}
