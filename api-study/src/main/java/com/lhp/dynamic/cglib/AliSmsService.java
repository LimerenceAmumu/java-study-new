package com.lhp.dynamic.cglib;

/**
 * 被代理类
 */
public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}