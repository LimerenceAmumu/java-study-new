package com.lhp.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/21 14:18
 */
@Slf4j
public class ClassNotFoundDemo {

    public static void main(String[] args) {

        // 随便写一个不存在的类名
        try {
            Class<?> aClass = Class.forName("com.lhp.jvm.NotExistClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        log.error("as");
        Student student = new Student();
        System.out.println("student = " + student.age);
        System.out.println("'sssssssss'");
    }
}
