package com.lhp.config;

import java.util.ResourceBundle;

/**
 * @author lihp
 * @date 2021年11月26日 11:02
 * 火石数据库信息
 */
public class DatasourceConfig {

    public static void main(String[] args) {
        DD();
    }

    public static void DD() {
        //config为属性文件名，放在包com.test.config下，如果是放在src下，直接用config即可
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String key = resource.getString("name");
        System.out.println("key = " + key);
    }
}
