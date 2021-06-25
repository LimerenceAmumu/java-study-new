package com.lhp.jvm;

/**
 * @author Amumu
 * @create 2019/9/22 16:07
 * volatile可见性
 */
class MyData{
    int a=0;
    void add(){
        this.a=100;
    }


}

public class VolatileTest {
    public static void main(String[] args) {

        seeOK();
    }

    public static void seeOK() {
        MyData myData = new MyData();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"----------a的值"+myData.a);
                Thread.sleep(3000);
                myData.add();
                System.out.println(Thread.currentThread().getName()+"----------a的值(更新后)"+myData.a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        while (myData.a==0){

        }
        System.out.println("---------over------------");
        System.out.println("a="+myData.a);
    }
}
