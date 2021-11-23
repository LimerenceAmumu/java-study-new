package com.lhp.fastJson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @Description: fastJson
 * @author: lihp
 * @date: 2021/9/29 3:34 下午
 */
public class TestClient {
    @Test
    public void test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 25L);
        jsonObject.put("name", "45455");
        jsonObject.put("per", 0.25d);
        jsonObject.put("date", LocalDateTime.now());

        System.out.println("jsonObject.get(\"id\") = " + jsonObject.get("id"));
        System.out.println("jsonObject.get(\"name\") = " + jsonObject.get("name"));
        System.out.println("jsonObject.get(\"per\") = " + jsonObject.get("per"));
        System.out.println("jsonObject.get(\"date\") = " + jsonObject.get("date"));

        Long adsa = jsonObject.getLong("adsa");
        System.out.println("adsa = " + adsa);


    }

}
