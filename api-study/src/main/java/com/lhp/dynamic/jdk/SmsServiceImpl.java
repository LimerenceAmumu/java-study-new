package com.lhp.dynamic.jdk;

/**
 * 被代理接口的实现类
 */
public class SmsServiceImpl implements SmsService {
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}