package com.lhp.client;

import com.lhp.annotation.MyAnnotation;

/**
 * 使用注解
 */
@MyAnnotation(value = "annotation on class")
public class Demo {

    @MyAnnotation(value = "annotation on field")
    public String name;

    @MyAnnotation(value = "annotation on method")
    public void hello() {}

    @MyAnnotation()
    public void defaultMethod() {}
}
