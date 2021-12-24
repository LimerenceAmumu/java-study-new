package com.lhp.stringDemo;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Amumu
 * @create 2021/3/14 17:16
 */
public class StringTestClient {

    /**
     *
     */
    @Test
    public void testLength() {

        String ss = "https://standard-new-data-govern-pifow.aihuoshi.net/";
        //https://standard-new-data-govern-pifow.aihuoshi.net/piflow-web/jwtLogin
        //https://standard-new-data-govern-pifow-api.aihuoshi.net/piflow-web/jwtLogin
        System.out.println(ss.length());
    }

    /**
     * 替换
     */
    @Test
    public void testFormat() {
        String ss = "申请编码：%s, name: %s";
        String format = String.format(ss, "AA", "lihepeng");
        System.out.println("format = " + format);
    }

    /**
     * 补齐
     */
    @Test
    public void testFill() {
        String s = StringUtils.leftPad("33", 5, "0");
        System.out.println("s = " + s);//00033
    }

    /**
     * String 最大长度
     */
    @Test
    public void testStringLength() {
        String collect = Stream.generate(() -> "s")
                .limit(Integer.MAX_VALUE)
                .collect(Collectors.joining());
        System.out.println("collect = " + collect.length());
    }

    @Test
    public void testSub() {
        String tableName = "public.test_lhp";
        String substring = tableName.substring(tableName.indexOf(".") + 1);
        System.out.println("substring = " + substring);


        String tableName2 = "test_lhp";
        String substring2 = tableName2.substring(tableName2.indexOf(".") + 1);
        System.out.println("substring = " + substring2);


        String testss = "206.5 kb";
        String testssRe = testss.substring(0, testss.indexOf(" "));
        System.out.println("testssRe = " + testssRe);

    }

    @Test
    public void testDiv() {

        int a = 15;
        int b = 36;
        System.out.println(a / b);
    }


    @Test
    public void testNumtoStr() {
        int a = 15;

        String ss = a + "";
        System.out.println("ss = " + ss);
    }


    //  where sql(查询条件)   where (   name !=null  and name != ''  )  or  (   sex !=null  and sex != ''  )  or  (   age !=null  and age != ''  )
    // select *
    // Select  count(*)
    // select

    /**
     * 截取SQL
     */
    @Test
    public void testSqlSub() {
        String sql = "select * from #{} whEre  ${column1} !=null  and ${column1} != '' ";

        //expect  select * from user whEre ( ${column1} !=null  and ${column1} != '')  or () or ()
        String[] bases = sql.split("(?i)where");

        System.out.println("wheres = " + bases);

        List<String> columns = Arrays.asList("name", "sex", "age");

        String baseStr = columns.get(0);
        String countSelect = "select count(*)";
        //获取有问题的 id

        ArrayList<String> dealResultColumns = new ArrayList<>();
        for (String column : columns) {
            String s = bases[1].replaceAll("\\$\\{column1\\}", column);
            column = " ( " + s + " ) ";
            dealResultColumns.add(column);
        }

        String join = Joiner.on(" or ").join(dealResultColumns);

        String result = bases[0] + "where" + join;

        System.out.println("result = " + result);
        //int where = sql.indexOf("(?i)where");  不能区分大小写 故只能采用正则 分隔字符串 的方式  (?i)  ：忽略大小写

        //StrUtil.
    }


}
