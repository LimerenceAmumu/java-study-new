package com.lhp.jvm;

/**
 * @Description: 栈
 * @author: lihp
 * @date: 2021/10/20 09:22
 */
public class StackTest {

    public static int aa = 0;

    public static void main(String[] args) {
        System.out.println(aa);
        int i = 0;
        for (int j = 0; j < 100; j++) {
            i = i++;
            System.out.println("i = " + i);
        }
        System.out.println(i);
    }

    private static Integer getStaticInteger(String num) {
        int a = 10;
        int b = 60;
        long c = 200L;
        String ss = "dadsadsasda";
        StackTest stackTest = new StackTest();
        return 200;
    }

    private Integer getInteger(String num) {
        int a = 10;
        int b = 60;
        long c = 200L;
        String ss = "dadsadsasda";
        StackTest stackTest = new StackTest();
        getStaticInteger(ss);
        return 200;
    }

    /**
     * slot的重复利用
     */
    private void repearSlot() {
        int a = 20;
        {
            int b = 30;
            //如果没有这句话，会被优化掉，局部变量表不会出现b
            System.out.println("b = " + b);
        }
        //c 会重复利用b的slot
        int c = 55;
    }
}
