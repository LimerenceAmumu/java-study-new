package com.lhp.hutool;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.junit.Test;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/12/7 17:03
 */
public class HttpReqTest {
    public static final String url = "https://lake-test.aihuoshi.net/data-work/data-source/sources/schema?metaDataType=SCHEMA";
    public static final String username = "root";
    public static final String pwd = "pwd";
    public static final String body = "{\"id\":\"1584560165577224194\",\"name\":\"mysql1\",\"code\":\"mysql1\",\"codeGenerateRule\":1,\"url\":\"192.168.110.40:3306\",\"username\":\"root\",\"password\":\"FxO+ITwaFsdfllF1BSYS4g==\",\"version\":\"8.0.25-commercial\",\"type\":\"MYSQL\",\"description\":null,\"status\":\"FAIL\",\"whitelist\":null,\"blacklist\":null,\"additionalConfig\":null,\"createUser\":\"admin\",\"createTime\":\"2022-10-24 22:59:18\",\"updateUser\":null,\"updateTime\":\"2022-11-26 15:38:22\",\"businessDomainCode\":\"hsmap\",\"authenticationType\":\"NONE\",\"authenticationConfig\":null,\"classifies\":null,\"assemblyType\":\"DOUBLE_SELECT\",\"database\":null,\"table\":null,\"inside\":null,\"projectManagementId\":null}";

    @Test
    public void test01() {

        HttpRequest httpRequest = new HttpRequest(url);
        httpRequest.basicAuth(username, pwd);
        httpRequest.body(body);
//        httpRequest.au

//        httpRequest.method(Method.POST);
        httpRequest.header("authorization", "ssssssss");
        HttpResponse response = httpRequest.execute();
        String body = response.body();


    }

}
