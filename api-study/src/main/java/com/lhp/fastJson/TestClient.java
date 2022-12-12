package com.lhp.fastJson;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.lhp.bean.Apple;
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
     * java 集合转 json 数组
     */
    @Test
    public void test01() {
        ArrayList<Apple> apples = new ArrayList<>(10);

        for (int i = 0; i < 9; i++) {
            Apple apple = new Apple();
            apple.setColor("sss");
            apple.setWeight(20);
            apples.add(apple);
        }
        String jsonString = JSONArray.toJSONString(apples);
        System.out.println("jsonString = " + jsonString);

        String jsonString2 = JSONArray.toJSONString(Collections.emptyList());
        System.out.println("jsonString2 = " + jsonString2);

        String jsonString3 = JSONArray.toJSONString(null);
        System.out.println("jsonString3 = " + jsonString2);

    }

    /**
     * json 转 List
     */
    @Test
    public void test03() {
        List<Apple> apples = JSONObject.parseArray("[{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20},{\"color\":\"sss\",\"weight\":20}]",
                Apple.class);

        System.out.println("apples = " + apples);

        List<Apple> applessssss = JSONObject.parseArray("[]", Apple.class);
        System.out.println("applesssss = " + applessssss);

        List<Apple> applesss = JSONObject.parseArray(null, Apple.class);
        System.out.println("applesss = " + applesss);
    }


    /**
     * 数组字符串 转List
     */
    @Test
    public void testAA() {

        List<String> errors = JSONObject.parseArray("[\"专题\", \"药选址\", \"审批阶段\", \"产品动态\"]", String.class);//把字符串转换成集合

        System.out.println("jsonObject = " + errors);

        String s = JSONArray.toJSONString(errors);
        System.out.println("s = " + s);

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

    /**
     * 从文件中读取json字符串
     */
    @Test
    public void test00() {
        String s = FileUtil.readString(new File("/Users/lihepeng/IdeaProjects/java-study/api-study/src/main/java/com/lhp/fastJson/json1.json"), "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(s);
        Set<String> strings = jsonObject.keySet();
        int size = jsonObject.keySet().size();
        System.out.println("size = " + size);
    }


    //读取 json   数据类型
    @Test
    public void test00Temp() {
        String s = FileUtil.readString(new File("/Users/lihepeng/IdeaProjects/java-study/api-study/src/main/java/com/lhp/fastJson/json1.json"), "utf-8");
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONObject address = (JSONObject) JSONPath.read(s, "address");
        Set<String> keySet = address.keySet();
        for (String s1 : keySet) {
            String v = address.getString(s1);
            String simpleName = address.get(s1).getClass().getSimpleName();
            System.out.println("simpleName = " + simpleName);
            //if()
            System.out.println(s1 + ":" + v);
        }
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
