package com.lhp;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lihp
 * @date 2021年11月25日 14:47
 */
public class Demo1 {
    public static void main(String[] args) {

        DataStandMetaStatusEnum[] values = DataStandMetaStatusEnum.values();
        for (DataStandMetaStatusEnum value : values) {
            System.out.println("value = " + value);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dd", 656);
        System.out.println("hello World");
    }
}
