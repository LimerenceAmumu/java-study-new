package com.lhp.sql;


import com.github.pagehelper.parser.CountSqlParser;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSqlTest {

    public static void main(String[] args) {
        getCount();
    }

    public static void getCount() {
        String sql = "SELECT t.f1, t.f2, count(t.f1) AS count FROM table AS t LEFT JOIN table2 AS t2 ON t.f1 = t2.f2 " +
                "LEFT JOIN table3 AS t3 ON t.f1 = t3.f2 WHERE t.f1 = '1222121' OR t.f2 = '122212111111' GROUP BY t.f1 ORDER BY t.f1, t.f2 DESC LIMIT 10, 2";
        String querySql = null;
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {
                Select select = (Select) statement;
                SelectBody selectBody = select.getSelectBody();
                if (selectBody instanceof PlainSelect) {
                    // 获取sql中的limit字段
                    Limit limit = ((PlainSelect) selectBody).getLimit();
                    if (limit != null) {
                        ((PlainSelect) selectBody).setLimit(null);
                    }
                }
                querySql = select.toString();
            } else {
                //throw new CustomException("查询语句不正确!");
            }
        } catch (Exception e) {

        }
        CountSqlParser parser = new CountSqlParser();
        String countSql = parser.getSmartCountSql(querySql);
        System.out.println(sql);
        System.out.println(querySql);
        System.out.println(countSql);
    }


    public static void createSelect() {
        System.out.println("==================================================创建查询====================================================");
        PlainSelect plainSelect = new PlainSelect();
        //创建查询的表
        Table table = new Table("table");
        table.setAlias(new Alias("t"));
        plainSelect.setFromItem(table);
        //创建查询的列
        List<String> selectColumnsStr = Arrays.asList("f1", "f2");


        List<SelectItem> expressionItemList = selectColumnsStr.stream().map(item -> {
            SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
            selectExpressionItem.setExpression(new Column(table, item));
            return (SelectItem) selectExpressionItem;
        }).collect(Collectors.toList());

        SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
        selectExpressionItem.setAlias(new Alias("count"));
        Function function = new Function();
        function.setName("count");
        ExpressionList expressionList = new ExpressionList();
        expressionList.setExpressions(Arrays.asList(new Column(table, "f1")));
        function.setParameters(expressionList);
        selectExpressionItem.setExpression(function);
        expressionItemList.add(selectExpressionItem);
        plainSelect.setSelectItems(expressionItemList);

        AtomicInteger atomicInteger = new AtomicInteger(1);
        List<Join> joinList = Stream.of(new String[2]).map(item -> {
            Join join = new Join();
            join.setLeft(true);
            Table joinTable = new Table();
            joinTable.setName("table" + atomicInteger.incrementAndGet());
            joinTable.setAlias(new Alias("t" + atomicInteger.get()));
            join.setRightItem(joinTable);
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(table, "f1"));
            equalsTo.setRightExpression(new Column(joinTable, "f2"));
            join.setOnExpression(equalsTo);
            return join;
        }).collect(Collectors.toList());
        plainSelect.setJoins(joinList);


        //条件
        EqualsTo leftEqualsTo = new EqualsTo();
        leftEqualsTo.setLeftExpression(new Column(table, "f1"));
        StringValue stringValue = new StringValue("1222121");
        leftEqualsTo.setRightExpression(stringValue);
        plainSelect.setWhere(leftEqualsTo);

        EqualsTo rightEqualsTo = new EqualsTo();
        rightEqualsTo.setLeftExpression(new Column(table, "f2"));
        StringValue stringValue1 = new StringValue("122212111111");
        rightEqualsTo.setRightExpression(stringValue1);
        OrExpression orExpression = new OrExpression(leftEqualsTo, rightEqualsTo);
        plainSelect.setWhere(orExpression);

        //分组
        GroupByElement groupByElement = new GroupByElement();
        groupByElement.setGroupByExpressions(Arrays.asList(new Column(table, "f1")));
        plainSelect.setGroupByElement(groupByElement);
        System.out.println(plainSelect);

        //排序
        OrderByElement orderByElement = new OrderByElement();
        orderByElement.setAsc(true);
        orderByElement.setExpression(new Column(table, "f1"));
        OrderByElement orderByElement1 = new OrderByElement();
        orderByElement1.setAsc(false);
        orderByElement1.setExpression(new Column(table, "f2"));

        //分页
        Limit limit = new Limit();
        limit.setRowCount(new LongValue(2));
        limit.setOffset(new LongValue(10));
        plainSelect.setLimit(limit);
        plainSelect.setOrderByElements(Arrays.asList(orderByElement, orderByElement1));
        System.out.println(plainSelect.toString());
        System.out.println("==================================================创建查询====================================================");
    }


    @Test
    public void changeSelect() {
        System.out.println("==================================================改变原有查询====================================================");
        CCJSqlParserManager parserManager = new CCJSqlParserManager();
        try {
            Select select = (Select) (parserManager.parse(new StringReader("select * from table")));
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            //创建查询的表
            Table table = new Table("table");
            table.setAlias(new Alias("t"));
            plainSelect.setFromItem(table);
            //创建查询的列
            List<String> selectColumnsStr = Arrays.asList("f1", "f2");

            List<SelectItem> expressionItemList = selectColumnsStr.stream().map(item -> {
                SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
                selectExpressionItem.setExpression(new Column(table, item));
                return (SelectItem) selectExpressionItem;
            }).collect(Collectors.toList());

            SelectExpressionItem selectExpressionItem = new SelectExpressionItem();
            selectExpressionItem.setAlias(new Alias("count"));
            Function function = new Function();
            function.setName("count");
            ExpressionList expressionList = new ExpressionList();
            expressionList.setExpressions(Arrays.asList(new Column(table, "f1")));
            function.setParameters(expressionList);
            selectExpressionItem.setExpression(function);
            expressionItemList.add(selectExpressionItem);
            plainSelect.setSelectItems(expressionItemList);

            AtomicInteger atomicInteger = new AtomicInteger(1);
            List<Join> joinList = Stream.of(new String[2]).map(item -> {
                Join join = new Join();
                join.setLeft(true);
                Table joinTable = new Table();
                joinTable.setName("table" + atomicInteger.incrementAndGet());
                joinTable.setAlias(new Alias("t" + atomicInteger.get()));
                join.setRightItem(joinTable);
                EqualsTo equalsTo = new EqualsTo();
                equalsTo.setLeftExpression(new Column(table, "f1"));
                equalsTo.setRightExpression(new Column(joinTable, "f2"));
                join.setOnExpression(equalsTo);
                return join;
            }).collect(Collectors.toList());
            plainSelect.setJoins(joinList);

            //条件
            EqualsTo leftEqualsTo = new EqualsTo();
            leftEqualsTo.setLeftExpression(new Column(table, "f1"));
            StringValue stringValue = new StringValue("1222121");
            leftEqualsTo.setRightExpression(stringValue);

            EqualsTo rightEqualsTo = new EqualsTo();
            rightEqualsTo.setLeftExpression(new Column(table, "f2"));
            StringValue stringValue1 = new StringValue("122212111111");
            rightEqualsTo.setRightExpression(stringValue1);
            OrExpression orExpression = new OrExpression(leftEqualsTo, rightEqualsTo);
            plainSelect.setWhere(orExpression);

            //分组
            GroupByElement groupByElement = new GroupByElement();
            groupByElement.setGroupByExpressions(Arrays.asList(new Column(table, "f1")));
            plainSelect.setGroupByElement(groupByElement);
            System.out.println(plainSelect);

            //排序
            OrderByElement orderByElement = new OrderByElement();
            orderByElement.setAsc(true);
            orderByElement.setExpression(new Column(table, "f1"));
            OrderByElement orderByElement1 = new OrderByElement();
            orderByElement1.setAsc(false);
            orderByElement1.setExpression(new Column(table, "f2"));

            //分页
            Limit limit = new Limit();
            limit.setRowCount(new LongValue(2));
            limit.setOffset(new LongValue(10));
            plainSelect.setLimit(limit);
            plainSelect.setOrderByElements(Arrays.asList(orderByElement, orderByElement1));
            System.out.println(plainSelect.toString());
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        System.out.println("==================================================改变原有查询====================================================");
    }


    @Test
    public void test01() {
        String sql = "select * from lhp";
        Statement statement = null;

        try {
            statement = CCJSqlParserUtil.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            if (selectBody instanceof PlainSelect) {
                Limit limit = ((PlainSelect) selectBody).getLimit();
                if (limit == null) {
                    limit = new Limit();
                }
                limit.setRowCount(new LongValue(2));
                limit.setOffset(new LongValue(0));
                ((PlainSelect) selectBody).setLimit(limit);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        String s = statement.toString();
        System.out.println("s = " + s);
    }


    @Test
    public void test() {
        Long offset = 2L;
        Long pageSize = 10L;
        String sql = "select * from db.tableName where userName = 1 and time between 2 and 3 ";
        Statement statement = null;
        try {
            statement = CCJSqlParserUtil.parse(sql);
            Select select = (Select) statement;
            SelectBody selectBody = select.getSelectBody();
            if (selectBody instanceof PlainSelect) {
                // 获取sql中的limit字段
                Limit limit = ((PlainSelect) selectBody).getLimit();
                if (limit == null || limit.isLimitNull()) {
                    // limit为空的时候加上默认的limit
                    if (limit == null) {
                        limit = new Limit();
                    }
                    limit.setRowCount(new LongValue(pageSize));
                    limit.setOffset(new LongValue(offset));
                    ((PlainSelect) selectBody).setLimit(limit);
                } else {
//                    LongValue longValue = (LongValue) limit.getRowCount();
//                    if (longValue.getValue() > 10) {
//                        longValue.setValue(2);
//                    }
//                    limit.setRowCount(longValue);
                }
            }
            System.out.println(select.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
