package com.lhp.dynamic.jdk;

/**
 * @Description: JDK 动态代理 测试类
 * @author: lihp
 * @date: 2021/9/16 11:13 上午
 */
public class JdkReflectTestClient {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("hello!");
    }
}
