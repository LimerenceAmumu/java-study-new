package com.lhp.fastJson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        List<String> errors = JSONObject.parseArray("[\"专题\", \"药选址\", \"审批阶段\", \"产品动态\"]", String.class);//把字符串转换成集合

        System.out.println("jsonObject = " + errors);


    }


    @Test
    public void test22() {

        ArrayList<String> titleEvent = new ArrayList<>();
        titleEvent.add("[\"专题\", \"药选址\", \"审批阶段\", \"产品动态\"]");
        titleEvent.add("[\"企业动态\", \"媒体评议\", \"专题\", \"药选址\", \"审批阶段\", \"产品动态\"]");

        Map<String, Long> map = titleEvent.stream()
                .filter(StrUtil::isNotBlank)
                .map(s -> JSONObject.parseArray(s, String.class))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("map = " + map);
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
