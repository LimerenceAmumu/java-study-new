package com.lhp.reflect;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/9/15 9:36 上午
 */

public class DemoClass {
    static {
        System.out.println("静态代码块---");
    }

    {
        System.out.println("非静态代码块---");

    }
}
