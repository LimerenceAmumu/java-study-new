package com.lhp.stringDemo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.lhp.DateDemo.DateUtil;
import com.xiaoleilu.hutool.convert.Convert;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
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


    @Test
    public void testWrap1() {
        String nihc = StringUtils.wrap("nihc", "=");
        System.out.println("nihc = " + nihc);

        System.out.println(StrUtil.wrap("你好", "（", ")"));

    }

    @Test
    public void testCompare() {
        DateTimeFormatter simpleDateFormat = DateUtil.HHMMSS_dateTimeFormatter;
        String ruleArgOne = "05:05:00";
        String ruleArgtwo = "05:35:00";
        LocalDateTime begin = LocalDateTime.parse(ruleArgOne, simpleDateFormat);
        LocalDateTime end = LocalDateTime.parse(ruleArgtwo, simpleDateFormat);


        Predicate<String> check = v -> {
            try {
                if (StringUtils.isBlank(v)) {
                    return false;
                }
                LocalDateTime cur = LocalDateTime.parse(v, simpleDateFormat);
                // 最小值比当前值大  校验不通过
                if (begin.compareTo(cur) == 1 || end.compareTo(cur) == -1) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        };

        System.out.println("check.test(\"2022-01-19 05:05:00\") = " + check.test("23:05:00"));
        System.out.println("check.test(\"2022-01-19 05:05:00\") = " + check.test("05:20:00"));

    }


    @Test
    public void test0000() {

        String dateStr = "2017-05-06";
        Convert.toBigDecimal(dateStr);

        String errorIds = "[{\"id1\": \'1\',\"id2\": \'3\'},{\"id1\": \'13\', \"id2\": \'33\'}]";
        List<JSONObject> errors = JSONObject.parseArray(errorIds, JSONObject.class);//把字符串转换成集合
        // select * from public.dont_del_lhp where id in('1','2')
        //select * from public.dont_del_lhp where (id,name) in ((1, 1), (2, 1), (3, 1),(5, 2))

        //处理分页信息
        String pageSql = "limit %d offset %d";
        String finalSqlResult = "";
        String finalSqlsqlCount = "";

        ArrayList<String> primaryColumn = new ArrayList<>();
        primaryColumn.add("id1");
        primaryColumn.add("id2");

        String sqlmain = " select * from " + "table_nam";
        if (primaryColumn.size() == 1) {
            //查询结果SQL
            String parmaryColumn = primaryColumn.get(0);
            ArrayList<String> idValues = new ArrayList<>();
            //单主键
            for (JSONObject error : errors) {
                String value = error.getString(parmaryColumn);
                idValues.add(value);
            }
            String idvalueStr = Joiner.on(",").join(idValues);
            idvalueStr = " ( " + idvalueStr + " ) ";
            String whereSql = " where " + primaryColumn.get(0) + " in " + idvalueStr;
            finalSqlResult = sqlmain + whereSql + pageSql;

            //获取总数SQL
            finalSqlsqlCount = "select count(*) as total from " + "table_nam" + whereSql;
        } else {
            //联合主键
            /**
             * [
             *   {
             *     "id1": 1,
             *     "id2": 3
             *   },
             *   {
             *     "id1": 13,
             *     "id2": 33
             *   }
             * ]
             */
// ((1, 1), (2, 1), (3, 1),(5, 2))
            ArrayList<String> allIdValue = new ArrayList<>(); //((1, 1), (2, 1), (3, 1),(5, 2))
            for (JSONObject error : errors) {
                ArrayList<String> perIdValue = new ArrayList<>();// 单个id值对(1, 1)
                for (String pName : primaryColumn) {
                    String value = error.getString(pName);
                    perIdValue.add(value);
                }
                String perIdValueStr = " ( " + Joiner.on(",").join(perIdValue) + " ) ";
                allIdValue.add(perIdValueStr);
            }
            String idvalueStr = StrUtil.wrap(Joiner.on(",").join(allIdValue), "( ", " ) ");
            //组合  where
            String idNameStr = StrUtil.wrap(Joiner.on(",").join(primaryColumn), "( ", " ) ");

            String whereSql = " where " + idNameStr + " in " + idvalueStr + pageSql;

            finalSqlResult = sqlmain + whereSql;
            finalSqlsqlCount = "select count(*) as total from " + "table_nam" + whereSql;
        }

        System.out.println("sqlmain = " + sqlmain);
    }


    @Test
    public void test02() {
        Boolean.valueOf(true);
    }

}
