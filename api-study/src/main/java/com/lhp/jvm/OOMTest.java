package com.lhp.jvm;

import java.util.ArrayList;

/**
 * @author Amumu
 * @create 2019/7/19 20:29
 */
public class OOMTest {
    byte[] bytes = new byte[1024 * 1024];

    public static void main(String[] args) throws InterruptedException {

        ArrayList<OOMTest> oomTests = new ArrayList<>();
        while (true){
            oomTests.add(new OOMTest());
            Thread.sleep(50);
        }
    }

}
