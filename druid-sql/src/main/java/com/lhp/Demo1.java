package com.lhp;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLUseStatement;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/4/14 09:13
 */
public class Demo1 {
    public static void main(String[] args) {
        Map<String, TreeSet<String>> fromTo = getFromTo("SELECT salesperson.name,max_sale.amount,max_sale.customer_name FROM salesperson,-- find maximum size and customer at same time LATERAL(SELECT amount, customer_name FROM all_sales WHERE all_sales.salesperson_id = salesperson.id ORDER BY amount DESC LIMIT 1)AS max_sale;WITH RECURSIVE employee_paths (id, name, path) AS(SELECT id, name, CAST(id AS CHAR(200))FROM employees WHERE manager_id IS NULL UNION ALL SELECT e.id, e.name, CONCAT(ep.path, ',', e.id)FROM employee_paths AS ep JOIN employees AS e ON ep.id = e.manager_id)");
        System.out.println("fromTo = " + fromTo);
    }

    public static Map<String, TreeSet<String>> getFromTo(String sql) throws ParserException {
        List<SQLStatement> stmts = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        TreeSet<String> fromSet = new TreeSet<>();
        TreeSet<String> toSet = new TreeSet<>();
        HashMap<String, TreeSet<String>> result = new HashMap<>();
        result.put("from", fromSet);
        result.put("to", toSet);
        if (stmts == null) {
            return null;
        }

        String database = "DEFAULT";
        for (SQLStatement stmt : stmts) {
            SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(JdbcConstants.POSTGRESQL);
            if (stmt instanceof SQLUseStatement) {
                database = ((SQLUseStatement) stmt).getDatabase().getSimpleName().toUpperCase();
            }
            stmt.accept(statVisitor);
            Map<TableStat.Name, TableStat> tables = statVisitor.getTables();
            if (tables != null) {
                final String db = database;
                tables.forEach((tableName, stat) -> {
                    if (stat.getCreateCount() > 0 || stat.getInsertCount() > 0) {
                        String to = tableName.getName().toUpperCase();
                        if (!to.contains(".")) {
                            to = db + "." + to;
                        }
                        toSet.add(to);
                    } else if (stat.getSelectCount() > 0) {
                        String from = tableName.getName().toUpperCase();
                        if (!from.contains(".")) {
                            from = db + "." + from;
                        }
                        fromSet.add(from);
                    }
                });
            }
        }
        return result;
    }
}


