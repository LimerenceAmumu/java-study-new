package com.lhp.jvm;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/21 14:18
 */
public class ClassNotFoundDemo {

    public static void main(String[] args) {

        // 随便写一个不存在的类名
        try {
            Class<?> aClass = Class.forName("com.lhp.jvm.NotExistClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Student student = new Student();
        System.out.println("student = " + student.age);
        System.out.println("'sssssssss'");
    }
}
