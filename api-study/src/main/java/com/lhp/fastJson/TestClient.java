package com.lhp.fastJson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.xiaoleilu.hutool.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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


    /**
     *
     */
    @Test
    public void testAA() {

        List<JSONObject> errors = JSONObject.parseArray("[]", JSONObject.class);//把字符串转换成集合

        System.out.println("jsonObject = " + errors);
    }

    @Test
    public void test00() {
        String s = FileUtil.readString(new File("/Users/lihepeng/IdeaProjects/java-study/api-study/src/main/java/com/lhp/fastJson/json1.json"), "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(s);
        Set<String> strings = jsonObject.keySet();
        int size = jsonObject.keySet().size();
        System.out.println("size = " + size);
    }


    @Test
    public void test2() {
        String s = FileUtil.readString(new File("/Users/lihepeng/IdeaProjects/java-study/api-study/src/main/java/com/lhp/fastJson/json1.json"), "utf-8");

        JSONObject jsonObject = JSONObject.parseObject(s);

        Object read = JSONPath.read(s, "job.content[0].reader.parameter.connection[0].table[0]");
        Object index = JSONPath.read(s, "job.content[0].writer.parameter.index");
        Object alias = JSONPath.read(s, "job.content[0].writer.parameter.alias66");
        JSONObject ret = new JSONObject();
        ret.put(read.toString(), new JSONArray(Arrays.asList(index, alias)));
        System.out.println("ret = " + ret);

        System.out.println("read = " + read);
    }


}
