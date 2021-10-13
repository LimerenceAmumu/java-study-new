package com.lhp.dynamic.jdk;

/**
 * 被代理的接口
 */
public interface SmsService {
    String send(String message);
}