package com.lhp.druid;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.clause.MySqlSelectIntoStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

import java.util.List;

/**
 * @author lihp
 * @date 2021年11月27日 11:35
 */
public class ClientOne {
    public static void main(String[] args) {


        String sql = " select a,b,l from ddd limit 5,20";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement = parser.parseSelect();
        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) sqlStatement;
        SQLSelect select = sqlSelectStatement.getSelect();
        SQLLimit limit = select.getLimit();
        limit.setOffset(99);
        limit.setRowCount(99);
        String s = sqlStatement.toString();
//        String sql = "insert into table_test_2 (farendma, hesuandm, hesuanmc, weihguiy, weihjigo, weihriqi, shijchuo) values\n" +
//                "('99996','HS205301','代码1','S####','101001','20140101',1414673101376), \n" +
//                "('99996','HS205401','代码2','S####','101001','20140101',1414673101376);";
//        MySqlStatementParser parser = new MySqlStatementParser(sql);
//        SQLStatement sqlStatement = parser.parseStatement();
//
//        MySqlInsertStatement insertStatement = (MySqlInsertStatement)sqlStatement;
//
//        //获取列的名称
//        List<SQLExpr> columnExprs = insertStatement.getColumns();
//        System.out.println("列的名称为：");
//        for(SQLExpr expr : columnExprs){
//            System.out.print(expr + "\t");
//        }
//        System.out.println();
//
//        //获取插入的值
//        List<SQLInsertStatement.ValuesClause> valuesClauseList = insertStatement.getValuesList();
//        System.out.println("值分别是：");
//        for(SQLInsertStatement.ValuesClause valuesClause : valuesClauseList){
//            List<SQLExpr> valueExprList = valuesClause.getValues();
//            for(SQLExpr expr : valueExprList){
//                System.out.print(expr + "\t");
//            }
//            System.out.println();
//        }
//
//    }
    }
}