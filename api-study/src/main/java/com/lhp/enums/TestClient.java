package com.lhp.enums;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/6/6 16:20
 */
public class TestClient {
    public static void main(String[] args) {

        DataLayer odS = DataLayer.getDataLayerByStr("OdS");
        System.out.println("odS = " + odS);
    }
}
