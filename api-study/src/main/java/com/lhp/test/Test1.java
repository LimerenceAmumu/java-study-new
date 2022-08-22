package com.lhp.test;


/**
 * @author: wwj
 * @description: TODO
 * @date: 2022/8/15 17:00
 * @version: 1.0
 */
public class Test1 {


    public static void main(String[] args) {
        Student x = new Student("小张", 1);
        Student y = new Student("小李", 2);
        Test1.swap(x, y);
        System.out.println("s1:" + x.getAge());
        System.out.println("s2:" + y.getAge());
    }

    public static void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x2:" + x.getAge());
        System.out.println("y2:" + y.getAge());
    }

}
