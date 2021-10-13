package com.lhp.dynamic.cglib;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/9/16 1:43 下午
 */
public class CglbTestClient {
    public static void main(String[] args) {
        AliSmsService aliSmsService = new AliSmsService();
        AliSmsService proxy = (AliSmsService) CglibProxyFactory.getProxy(aliSmsService.getClass());
        proxy.send("hello!");
    }
}
