package com.lhp.client;

import com.lhp.annotation.MyAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Amumu
 * @create 2021/3/9 21:38
 *  定义-》标注-》读取
 *
 * 读取注解
 */
public class TestClient {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Class<Demo> clazz = Demo.class;
        //获取类上的注解
        MyAnnotation annotationOnClass = clazz.getAnnotation(MyAnnotation.class);
        System.out.println("annotation.value() = " + annotationOnClass.value());
        //获取方法上的
        Method hello = clazz.getMethod("hello", null);
        MyAnnotation annotationOnMethod = hello.getAnnotation(MyAnnotation.class);
        System.out.println("annotationOnMethod.value() = " + annotationOnMethod.value());
        // 获取变量上的
        Field name = clazz.getField("name");
        MyAnnotation annotationOnField = name.getAnnotation(MyAnnotation.class);
        System.out.println("annotationOnField = " + annotationOnField.value());
    }
}
