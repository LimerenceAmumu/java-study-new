package com.lhp.collections.hutool;

import cn.hutool.core.collection.CollectionUtil;
import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/10/10 9:57 上午
 */
public class CollectionUtilTest {

    @Test
    public void testSub() {
        List<String> collect = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());

        // start： 包含  end 不包含 ，可以尽量把end写大
        List<String> sub = CollectionUtil.sub(collect, 3, 7);
        sub.forEach(System.out::println);
    }

    //http://122.51.174.150/fronts/今年也要加油鸭.ttf
    @Test
    public void test22() {
        String encode = URLDecoder.decode("http://122.51.174.150/%E4%BB%8A%E5%B9%B4%E4%B9%9F%E8%A6%81%E5%8A%A0%E6%B2%B9%E9%B8%AD.ttf");
        System.out.println("encode = " + encode);


        String encode1 = URLEncoder.encode("今年也要加油鸭.ttf");
        System.out.println("encode1 = " + encode1);

    }


    @Test
    public void test33() {

        String sql = "   select md5( string_agg(A.address_city,B.label_name1)) as id,\n" +
                "COALESCE(A.address_city, '安徽省'::text) AS address_city,\n" +
                "-- \"array_agg\"(distinct address_city),\n" +
                "COALESCE(B.label_name1, '全产业'::character varying) AS label_name1,\n" +
                "count(DISTINCT A.park_id) as value\n" +
                "from ads_client_main_park_info A\n" +
                "join ads_brain_label_park_info B\n" +
                "on A.park_id=B.entity_id and (B.label_name1 IS NOT NULL)and B.is_exist=1\n" +
                "where A.is_exist=1 and level_type='国家级'AND (A.park_type <> '化工园区'::text)\n" +
                "--GROUP BY  B.label_name1\n" +
                "GROUP BY cube \r(A.address_city,B.label_name1)\n   \r ";

        System.out.println("sql.replaceAll(\"\\n\",\"\") = " + sql.replaceAll("\n", " ").replaceAll("\r", " "));

    }


    @Test
    public void test332() {
        String text = "  select md5( string_agg(A.address_city,B.label_name1)) as id,\n" +
                "COALESCE(A.address_city, '安徽省'::text) AS address_city,\n" +
                "-- \"array_agg\"(distinct address_city),\n" +
                "COALESCE(B.label_name1, '全产业'::character varying) AS label_name1,\n" +
                "count(DISTINCT A.park_id) as value\n" +
                "from ads_client_main_park_info A\n" +
                "join ads_brain_label_park_info B\n" +
                "on A.park_id=B.entity_id and (B.label_name1 IS NOT NULL)and B.is_exist=1\n" +
                "where A.is_exist=1 and level_type='国家级'AND (A.park_type <> '化工园区'::text)\n" +
                "--GROUP BY  B.label_name1\n" +
                "GROUP BY cube (A.address_city,B.label_name1)";


        Pattern p = Pattern.compile("(?ms)('(?:''|[^'])*')|--.*?$|//.*?$|/\\*.*?\\*/|#.*?$|");

        String presult = p.matcher(text).replaceAll("$1");


        System.out.println(presult);


        presult = presult.replaceAll("[\b\r\n\t]", " ");

        System.out.println("text = " + presult);
    }


}
