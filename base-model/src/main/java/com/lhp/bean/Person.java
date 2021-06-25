package com.lhp.bean;

/**
 * @author Amumu
 * @create 2019/7/24 7:59
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    private void eat(String food){
        System.out.println("eat..."+food);
    }
}
