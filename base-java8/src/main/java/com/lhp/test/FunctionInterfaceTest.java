package com.lhp.test;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.lhp.bean.Apple;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Amumu
 * @create 2020/9/2 15:17
 * 函数式接口
 * predicate  打印筛选过后的苹果
 * consumer   打印苹果重量
 * function   获取苹果归属地列表 List<Apple> -> List<String>
 */
public class FunctionInterfaceTest {
    static Predicate<Apple> redApple = (a) -> {
        return "red".equals(a.getColor());
    };

    public static void main(String[] args) {

        init();
        filter(apples, redApple);

        consumer(apples, (a) -> System.out.println("a.getWeight() = " + a.getWeight()));

        List<Integer> function = function(apples, apple ->
                apple.getWeight()
        );
        function.forEach(System.out::println);

    }

    public static <T, R> List<R> function(List<T> datas, Function<T, R> function) {
        ArrayList<R> rs = new ArrayList<>();
        for (T data : datas) {
            rs.add(function.apply(data));
        }
        return rs;
    }

    public static <T> void consumer(List<T> datas, Consumer<T> consumer) {

//        consumer.accept();
        for (T data : datas) {
            consumer.accept(data);
        }
    }

    //一个过滤apple方法
    //1筛选出红
    public static <T> void filter(List<T> datas, Predicate<T> predicate) {
        for (T t : datas) {

            if (predicate.test(t)) {
                System.out.println("apple = " + t);
            }
        }
    }

    static List<Apple> apples = new ArrayList();

    public static void init() {

        apples.add(new Apple("red", null, "12"));
        apples.add(new Apple("red", null, "24"));
        apples.add(new Apple("yellow", 80, "12"));
        apples.add(new Apple("black", null, "12"));
        apples.add(new Apple("green", 200, "30"));
        apples.add(new Apple("origin", 110, "12"));
        apples.add(new Apple("purple", 110, "12"));
        apples.add(new Apple("red", 150, "12"));
        apples.add(new Apple("red", 60, "12"));
    }

    /**
     * 指定字段排序
     */
    @Test
    public void test1231() {
        init();
        ArrayList<Apple> head = new ArrayList<>();
        //要根据此顺序排序的
        List<String> sortKey = Arrays.asList("green", "yellow", "black", "origin");
        for (Apple apple : apples) {
            if (sortKey.contains(apple.getColor())) {
                head.add(apple);
            }
        }
        apples.removeAll(head);
        apples.addAll(0, head);
        String sortKeyStr = head.stream().map(Apple::getColor).collect(Collectors.joining(","));


        System.out.println("apples = " + apples);
    }

    /**
     * flatMap
     */
    @Test
    public void test22() {
        List<String> fun1 = Arrays.asList("one", "two", "three");
        List<String> fun2 = Arrays.asList("four", "five", "six");
        //Stream.of(fun1,fun2).flatMap(List::stream).forEach(System.out::print);

        //
        List<List<String>> lists = Arrays.asList(fun1, fun2);
        lists.stream()
                .flatMap(strings -> strings.stream())
                .forEach(System.out::print);
        System.out.println("lists = ");
        lists.stream()
                .map(strings -> strings.stream())
                .forEach(System.out::print);
    }


    @Test
    public void test212(){
        init();
        Map<String, List<Apple>> collect = apples.stream().sorted(Comparator.comparing(Apple::getFrom)).collect(groupingBy(Apple::getColor));
        System.out.println("collect = " + collect);
    }


    @Test
    public void testCode(){

        String nihao = DigestUtils.sha1Hex("nihao");
        String s = SecureUtil.sha1("nihao");
        System.out.println(nihao.equals(s));


        //第一种：以AES算法
        String content = "test中文";
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES.getValue(),key);


        //加密
        String encrypt = aes.encryptHex(content);
        //解密
        String str = aes.decryptStr(encrypt);

        //加密16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println("AES加密16进制表示：" + encryptHex);   //46953def8ec02e21f7c9bb4405243a70
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("AES解密为字符串：" + decryptStr);  //test中文


    }


    @Test
    public void test666() {


        String source = "{\n" +
                "  \"taskID\":\"11111\",\n" +
                "  \"columnMap\":\"id:id,name:name\",\n" +
                "  \"source\":{\n" +
                "    \"type\":\"jdbc\",\n" +
                "    \"config\":{\n" +
                "      \"jdbcUrl\":\"jdbc:mysql://192.168.110.40:3306/test\",\n" +
                "      \"username\":\"root\",\n" +
                "      \"password\":\"Supcon_21\",\n" +
                "      \"query\":\"select * from cjtest\",\n" +
                "      \"offsetColumns\":\"id\",\n" +
                "      \"sourceColumns\":\"id:string,name:string\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"sink\":{\n" +
                "    \"type\":\"jdbc\",\n" +
                "    \"config\":{\n" +
                "      \"jdbcUrl\":\"jdbc:dm://192.168.110.40:5236/SYSDBA\",\n" +
                "      \"username\":\"SYSDBA\",\n" +
                "      \"password\":\"SYSDBA\",\n" +
                "      \"targetTable\":\"SYSDBA.CJTEST\",\n" +
                "      \"writeMode\":\"append\",\n" +
                "      \"mergeKeys\":\"id\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String encode = Base64.encode(source, StandardCharsets.UTF_8);


        System.out.println("encode = " + encode);


        String s = new String(Base64.decode(encode), StandardCharsets.UTF_8);
        System.out.println("s = " + s);
    }

}
