package com.lhp.sql;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.parser.CountSqlParser;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JSqlParserUtil {


    /**
     * 构建查询sql对应的countSql
     *
     * @param sql
     * @return
     */
    @SneakyThrows
    public static String buildCountSql(String sql) {
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {
                Select select = (Select) statement;
                SelectBody selectBody = select.getSelectBody();
                if (selectBody instanceof PlainSelect) {
                    /**
                     *  mybatis CountSqlParser获取的countSql不会处理limit offset,
                     *  先将limit offset去掉再获取countSql
                     */
                    Limit limit = ((PlainSelect) selectBody).getLimit();
                    if (limit != null) {
                        ((PlainSelect) selectBody).setLimit(null);
                    }
                    Offset offset = ((PlainSelect) selectBody).getOffset();
                    if (offset != null) {
                        ((PlainSelect) selectBody).setOffset(null);
                    }
                }
                sql = select.toString();
                CountSqlParser parser = new CountSqlParser();
                String countSql = parser.getSmartCountSql(sql);
                log.debug("generate sql:{}", countSql);
                return countSql;
            } else {
                throw new SQLException("查询语句不正确!");
            }
        } catch (Exception e) {
            log.error(">>>获取countSql错误:", e);
            throw new SQLException("sql解析失败!");
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        Select stmt = (Select) CCJSqlParserUtil
                .parse("SELECT col1 AS a, col2 AS b, col3 AS c FROM table WHERE col1 = 10 AND col2 = 20 AND col3 = 30");

        Map<String, Expression> map = new HashMap<>();
        for (SelectItem selectItem : ((PlainSelect) stmt.getSelectBody()).getSelectItems()) {
            selectItem.accept(new SelectItemVisitorAdapter() {
                @Override
                public void visit(SelectExpressionItem item) {
                    map.put(item.getAlias().getName(), item.getExpression());
                }
            });
        }

        System.out.println("map " + map);
    }

    public static String buildPageSql(String sql, Long current, Long pageSize) {
        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            if (statement instanceof Select) {
                Select select = (Select) statement;
                SelectBody selectBody = select.getSelectBody();
                if (selectBody instanceof PlainSelect) {
                    Limit limit = new Limit();
                    limit.setRowCount(new LongValue(pageSize));
                    ((PlainSelect) selectBody).setLimit(limit);
                    Offset offset = new Offset();
                    offset.setOffset((current - 1) * pageSize);
                    ((PlainSelect) selectBody).setOffset(offset);
                }
                sql = select.toString();
                log.debug("generate sql:{}", sql);
                return sql;
            } else {
                throw new SQLException("查询语句不正确!");
            }
        } catch (Exception e) {
            log.error(">>>获取countSql错误:", e);

            return "";
        }
    }

    /**
     * 构建通过主键获取指定数据sql
     *
     * @param tableName 表名
     * @param pkParam   主键信息（可传多个联合主键，key为主键列名，value为主键值）
     * @return
     */
    public static String buildGetByPkSql(String tableName, JSONObject pkParam) {
        PlainSelect plainSelect = new PlainSelect();
        //创建查询的表
        plainSelect.setFromItem(new Table(tableName));
        //创建查询的列
        List<SelectItem> selectItemList = new ArrayList<>();
        selectItemList.add(new AllColumns());
        plainSelect.setSelectItems(selectItemList);
        //where条件
        int i = 0;
        LinkedExpression linkedExpression = null;
        for (String columnName : pkParam.keySet()) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(columnName));
            equalsTo.setRightExpression(getColumnValue(pkParam.get(columnName)));
            if (i == 0) {
                linkedExpression = new LinkedExpression(equalsTo);
            } else {
                Expression expression = linkedExpression.getLast().getExpression();
                AndExpression andExpression = new AndExpression(expression, equalsTo);
                linkedExpression.add(andExpression);
            }
            i++;
        }
        plainSelect.setWhere(linkedExpression.getLast().getExpression());
        String sql = plainSelect.toString();
        log.debug("generate sql:{}", sql);
        return sql;
    }

    public static String buildQueryMyDataSql(String tableName, JSONObject pkParam, String username) {
        PlainSelect plainSelect = new PlainSelect();
        //创建查询的表
        plainSelect.setFromItem(new Table(tableName));
        //创建查询的列
        List<SelectItem> selectItemList = new ArrayList<>();
        selectItemList.add(new AllColumns());
        plainSelect.setSelectItems(selectItemList);
        //where条件
        int i = 0;
        LinkedExpression linkedExpression = null;
        for (String columnName : pkParam.keySet()) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(columnName));
            equalsTo.setRightExpression(getColumnValue(pkParam.get(columnName)));
            if (i == 0) {
                linkedExpression = new LinkedExpression(equalsTo);
            } else {
                Expression expression = linkedExpression.getLast().getExpression();
                AndExpression andExpression = new AndExpression(expression, equalsTo);
                linkedExpression.add(andExpression);
            }
            i++;
        }
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(new Column("create_by"));
        equalsTo.setRightExpression(new StringValue(username));
        AndExpression andExpression = new AndExpression(linkedExpression.getLast().getExpression(), equalsTo);
        plainSelect.setWhere(andExpression);
        String sql = plainSelect.toString();
        log.debug("generate sql:{}", sql);
        return sql;
    }

    /**
     * 构建insert sql
     *
     * @param tableName 表名
     * @param dataParam 数据
     * @return
     */
    public static String builderInsertSql(String tableName, JSONObject dataParam) {
        Insert insert = new Insert();
        insert.setTable(new Table(tableName));

        List<Column> columnList = new ArrayList<>();
        MultiExpressionList multiExpressionList = new MultiExpressionList();
        List<Expression> expressionList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : dataParam.entrySet()) {
            String columnName = entry.getKey();
            Object columnValue = entry.getValue();
            columnList.add(new Column(columnName));
            expressionList.add(getColumnValue(columnValue));
        }
        insert.setColumns(columnList);
        multiExpressionList.addExpressionList(expressionList);
        insert.setItemsList(multiExpressionList);
        String sql = insert.toString();
        log.debug("generate sql:{}", sql);
        return sql;
    }

    /**
     * 构建update sql
     *
     * @param tableName 表名
     * @param pkParam   主键信息（可传多个联合主键，key为主键列名，value为主键值）
     * @param dataParam 数据
     * @return
     */
    public static String builderUpdateSql(String tableName, JSONObject pkParam, JSONObject dataParam) {
        Update update = new Update();
        update.setTable(new Table(tableName));

        List<Column> columnList = new ArrayList<>();
        List<Expression> expressionList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : dataParam.entrySet()) {
            String columnName = entry.getKey();
            Object columnValue = entry.getValue();
            columnList.add(new Column(columnName));
            expressionList.add(getColumnValue(columnValue));
        }
        update.setColumns(columnList);
        update.setExpressions(expressionList);

        //where条件
        int i = 0;
        LinkedExpression linkedExpression = null;
        for (String columnName : pkParam.keySet()) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(columnName));
            equalsTo.setRightExpression(getColumnValue(pkParam.get(columnName)));
            if (i == 0) {
                linkedExpression = new LinkedExpression(equalsTo);
            } else {
                Expression expression = linkedExpression.getLast().getExpression();
                AndExpression andExpression = new AndExpression(expression, equalsTo);
                linkedExpression.add(andExpression);
            }
            i++;
        }
        update.setWhere(linkedExpression.getLast().getExpression());
        String sql = update.toString();
        log.debug("generate sql:{}", sql);
        return sql;
    }

    /**
     * 构建delete sql
     *
     * @param tableName 表名
     * @param pkParam   主键信息（可传多个联合主键，key为主键列名，value为主键值）
     * @return
     */
    public static String builderDeleteSql(String tableName, JSONObject pkParam) {
        Delete delete = new Delete();
        delete.setTable(new Table(tableName));

        //where条件
        int i = 0;
        LinkedExpression linkedExpression = null;
        for (String columnName : pkParam.keySet()) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(columnName));
            equalsTo.setRightExpression(getColumnValue(pkParam.get(columnName)));
            if (i == 0) {
                linkedExpression = new LinkedExpression(equalsTo);
            } else {
                Expression expression = linkedExpression.getLast().getExpression();
                AndExpression andExpression = new AndExpression(expression, equalsTo);
                linkedExpression.add(andExpression);
            }
            i++;
        }
        delete.setWhere(linkedExpression.getLast().getExpression());
        String sql = delete.toString();
        log.debug("generate sql:{}", sql);
        return sql;
    }

    private static Expression getColumnValue(Object value) {
        if (value == null) {
            return new NullValue();
        } else if (value instanceof Long) {
            return new LongValue((Long) value);
        } else if (value instanceof Integer) {
            return new LongValue((Integer) value);
        } else {
            return new StringValue((String) value);
        }
    }

    /**
     * 使用一个单向链表拼接查询where条件
     */
    @Data
    static class LinkedExpression {
        private Expression expression;
        private LinkedExpression next;

        LinkedExpression(Expression expression) {
            this.expression = expression;
        }

        public void add(Expression expression) {
            LinkedExpression next = new LinkedExpression(expression);
            setNext(next);
        }

        public LinkedExpression getLast() {
            LinkedExpression tmp = this;
            while (tmp != null && tmp.next != null) {
                tmp = tmp.next;
            }
            return tmp;
        }
    }

}
