package com.lhp;

import com.google.common.base.Joiner;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.junit.Test;

import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/11/29 19:09
 */
public class DemoJdbc {

    public static final String url = "jdbc:postgresql://192.168.200.55:5432/postgres";
    public static final String driver = "org.postgresql.Driver";
    public static final String username = "postgres";
    public static final String pwd = "Vvb0rx3Tz32cy12f";

    private static HikariDataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(1);
        dataSource.setJdbcUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        dataSource.setConnectionTimeout(Duration.ofSeconds(2000).toMillis());
        return dataSource;
    }

    private static List<FieldInfo> getFieldInfos(String schema, String table, DatabaseMetaData metaData, Connection connection) throws SQLException {
        // 获取表列名
        ResultSet columnSet = metaData.getColumns(schema, null, table, null);
        ArrayList<String> columns = new ArrayList<>();
        while (columnSet.next()) {
            String column_name = columnSet.getString("COLUMN_NAME");
            columns.add(column_name);
        }

        // 获取类型
        String column = Joiner.on(",").join(columns);

        Statement statement = connection.createStatement();
        String queryColumnSql = "select " + column + " from " + table + " where 1=2";
        //从这里取出字段类型的 int值
        ResultSet rs = statement.executeQuery(queryColumnSql);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int i = 0, len = rsMetaData.getColumnCount(); i < len; i++) {

            String columnName = rsMetaData.getColumnName(i + 1);
            int columnType = rsMetaData.getColumnType(i + 1);

            JDBCType jdbcType = JDBCType.valueOf(columnType);
            String columnTypeName = rsMetaData.getColumnTypeName(i + 1);
            if ("bigserial".equals(columnTypeName)) {
                columnTypeName = "int8";
            }
            System.out.println("columnTypeName = " + columnTypeName);
        }


        List<FieldInfo> fieldInfos = new ArrayList<>();
        //获得主键
        ResultSet primarySet = metaData.getPrimaryKeys(schema, null, table);
        List<String> keys = new ArrayList<>();
        while (primarySet.next()) {
            keys.add(primarySet.getString("COLUMN_NAME"));
        }
        while (columnSet.next()) {
            FieldInfo fieldInfo = FieldInfo.builder()
                    .code(columnSet.getString("COLUMN_NAME"))
                    .size(columnSet.getString("COLUMN_SIZE"))
                    .isNullable(columnSet.getString("IS_NULLABLE").equals("YES"))
                    .defaultValue(columnSet.getString("COLUMN_DEF"))
                    .name(columnSet.getString("REMARKS"))
                    .isKey(false)
                    .build();
            fieldInfo.setColumnType(fieldInfo.buildColumnType(columnSet.getString("TYPE_NAME"),
                    fieldInfo.getSize(),
                    columnSet.getString("DECIMAL_DIGITS")));
            if (keys.contains(fieldInfo.getCode())) {
                fieldInfo.setIsKey(true);
            }
            fieldInfos.add(fieldInfo);
        }
        fieldInfos.sort(Comparator.comparing(FieldInfo::getCode));

        return fieldInfos;
    }

    @Test
    public void test22() {
        String type = "geometry";
        JDBCType jdbcType = JDBCType.valueOf(type);
        Integer vendorTypeNumber = jdbcType.getVendorTypeNumber();
        System.out.println("vendorTypeNumber = " + vendorTypeNumber);
    }

    /**
     * 获取jdbc 标准 类型
     *
     * @param schema
     * @param table
     * @param metaData
     * @param connection
     * @throws SQLException
     */
    private void getJdbcType(String schema, String table, DatabaseMetaData metaData, Connection connection) throws SQLException {
        // 获取表列名
        ResultSet columnSet = metaData.getColumns(schema, null, table, null);
        ArrayList<String> columns = new ArrayList<>();
        while (columnSet.next()) {
            String column_name = columnSet.getString("COLUMN_NAME");
            columns.add(column_name);
        }

        // 获取类型
        String column = Joiner.on(",").join(columns);
        Statement statement = connection.createStatement();
        // 组装sql
        // 组装查询sql  不需要查询出结果 1=2
        String queryColumnSql = "select " + column + " from " + table + " where 1=2";
        //从这里取出字段类型的 int值
        ResultSet rs = statement.executeQuery(queryColumnSql);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int i = 0, len = rsMetaData.getColumnCount(); i < len; i++) {
            String columnName = rsMetaData.getColumnName(i + 1);
            System.out.println("columnName = " + columnName);
            int columnType = rsMetaData.getColumnType(i + 1);
            System.out.println("columnType = " + columnType);
            //获取标准jdbcType  这个在所有数据库都是通用的
            JDBCType jdbcType = JDBCType.valueOf(columnType);
            System.out.println("jdbcType = " + jdbcType);
            String columnTypeName = rsMetaData.getColumnTypeName(i + 1);
            if ("bigserial".equals(columnTypeName)) {
                columnTypeName = "int8";
            }
            System.out.println("columnTypeName = " + columnTypeName);
        }
    }

    @SneakyThrows
    @Test
    public void test() {
        HikariDataSource dataSource = getDataSource();
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        List<FieldInfo> fieldInfos = getFieldInfos("public", "ods_asset_data_asset", metaData, connection);
    }

}
