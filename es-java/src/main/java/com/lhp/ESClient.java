package com.lhp;

import cn.hutool.core.util.StrUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.mapping.GetMapping;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/27 09:59
 */
public class ESClient {

    String url = "http://192.168.200.236:9200";
    String user = "lake-ro";
    String passWord = "T7kB5OpY1k+mavBH";
    String explodeFiled = "Value.data";
    String index = "product_rp_list_v001";
    JestClient jestClient = null;

    @SneakyThrows
    @Before
    public void init() {
        jestClient = getJestClient(url, user, passWord);

    }

    @Test
    public void test() {
        String sad = "";
        String[] explodeFileds = sad.split("\\.");
        System.out.println("explodeFileds = " + explodeFileds);
    }

    @SneakyThrows
    @Test
    public void test1() {
        String[] explodeFileds = explodeFiled.split("\\.");

        GetMapping getMapping = new GetMapping.Builder().build();
        JestResult jestResult = jestClient.execute(getMapping);
        JsonObject jsonObject = jestResult.getJsonObject()
                .getAsJsonObject(index)
                .getAsJsonObject("mappings")
                .getAsJsonObject("properties")
                .getAsJsonObject(explodeFileds[0])//需替换
                .getAsJsonObject("properties")//
                .getAsJsonObject(explodeFileds[1])//需替换
                .getAsJsonObject("properties");
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        List<String> collect = entries.stream().map(e -> e.getKey()).collect(Collectors.toList());

        System.out.println("collect = " + collect);
//        List<String> resultList = new ArrayList<>(jsonObject.keySet());
//        resultList.remove("doc");
//        resultList.remove("doc_as_upsert");
    }

    @Test
    public void test2() throws IOException {
        //es 7
        GetMapping getMapping = new GetMapping.Builder().build();
        JestResult jestResult = jestClient.execute(getMapping);
        List<String> explodeFiled = getExplodeFiled(jestResult, "Value.data", index, "");

        System.out.println("explodeFiled = " + explodeFiled);
    }

    private List<String> getExplodeFiled(JestResult jestResult, String explodeFiled, String index, String type) {
        String[] explodeFileds = explodeFiled.split("\\.");
        JsonObject jsonObject = jestResult.getJsonObject()
                .getAsJsonObject(index)
                .getAsJsonObject("mappings");
        // es6 特殊处理
        if (StrUtil.isNotBlank(type)) {
            jsonObject = jsonObject.getAsJsonObject("type");
        }
        jsonObject = jsonObject.getAsJsonObject("properties")
                .getAsJsonObject(explodeFileds[0])//需替换
                .getAsJsonObject("properties")//
                .getAsJsonObject(explodeFileds[1])//需替换
                .getAsJsonObject("properties");

        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        List<String> columns = entries.stream().map(e -> e.getKey()).collect(Collectors.toList());
        return columns;
    }

    public JestClient getJestClient(String url, String user, String passWord) {
        JestClientFactory factory = new JestClientFactory();
        HttpClientConfig.Builder httpClientConfig = new HttpClientConfig.Builder(url)
                .setPreemptiveAuth(new HttpHost(url))
                .multiThreaded(false)
                .connTimeout(30000)
                .readTimeout(300000)
                .maxTotalConnection(200)
                .requestCompressionEnabled(false)
                .discoveryEnabled(false)
                .discoveryFrequency(5L, TimeUnit.MINUTES);

        if (!("".equals(user) || "".equals(passWord))) {
            httpClientConfig.defaultCredentials(user, passWord);
        }

        factory.setHttpClientConfig(httpClientConfig.build());

        return factory.getObject();

    }

    @Test
    public void test03() {
        JestClient jestClient = getJestClient(url, user, passWord);

        GetMapping getMapping = new GetMapping.Builder().build();
        JestResult jestResult = null;
        try {
            jestResult = jestClient.execute(getMapping);
            //返回结果
            JsonObject jsonObject = jestResult.getJsonObject().getAsJsonObject("yujq").getAsJsonObject("mappings").getAsJsonObject("properties");

            List<String> fieldNames = new ArrayList<>(jsonObject.keySet());
            fieldNames.remove("doc");
            fieldNames.remove("doc_as_upsert");

            for (String fieldName : fieldNames) {

                String type;

                JsonElement type1 = jsonObject.getAsJsonObject(fieldName).get("type");
                if (type1 == null) {
                    type = "NESTED";
                } else {
                    type = jsonObject.getAsJsonObject(fieldName).get("type").toString();

                }

                if (StringUtils.isNotBlank(type) && type.startsWith("\"")) {
                    type = type.replaceAll("\"", "");
                    //  System.out.println("type = " + type);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test22() {
        String type = "\"12\"";
        System.out.println("type = " + type);

        if (StringUtils.isNotBlank(type) && type.startsWith("\"")) {
            type = type.replaceAll("\"", "");
            System.out.println("type = " + type);
        }

    }


    @Test
    public void test222() {
        try (JestClient jestClient = getJestClient(url, user, passWord)
        ) {
            GetMapping getMapping = new GetMapping.Builder().build();
            JestResult jestResult = jestClient.execute(getMapping);
            JsonObject jsonObject = jestResult.getJsonObject();
            JsonElement status = jsonObject.get("status");
            if (status != null && Objects.equals(status.getAsString(), 403)) {
            }
            for (String indexName : jsonObject.keySet()) {
                if (!indexName.startsWith(".") && !indexName.contains("apm-7")) {
                    System.out.println(indexName);
                }
            }

        } catch (IOException e) {
        }
    }

}
