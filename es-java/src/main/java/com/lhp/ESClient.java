package com.lhp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaoleilu.hutool.util.StrUtil;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.mapping.GetMapping;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/27 09:59
 */
public class ESClient {

    String url = "http://192.168.110.62:18054";
    String user = "aa";
    String passWord = "aa";
    String explodeFiled = "Value.data";
    String index = "sfzchannel_mm_73667a6368616e6e656c5f6d6d";
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
}
