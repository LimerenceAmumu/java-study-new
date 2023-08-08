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
import java.util.concurrent.TimeUnit;

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
        dataSource.setConnectionTimeout(Duration.ofSeconds(10).toMillis());
        return dataSource;
    }

    public static void main(String[] args) throws SQLException {
        HikariDataSource dataSource = getDataSource();

//        for (int i = 0; i < 5; i++) {
        Connection connection = dataSource.getConnection();
//            System.out.println("connection = " + connection);
//        }
        dataSource = null;
        //手动创建一些大对象
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            objects.add(new BigData());
        }
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("======");


        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(connection.isClosed());

        }
    }

    static class BigData {
        byte[] data = new byte[50 * 1024 * 1024];
    }

    private static List<FieldInfo> getFieldInfosBySql(String schema, Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();
        connection.setSchema(schema);
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        for (int i = 0, len = rsMetaData.getColumnCount(); i < len; i++) {

            String columnName = rsMetaData.getColumnName(i + 1);
            int columnType = rsMetaData.getColumnType(i + 1);

            JDBCType jdbcType = JDBCType.valueOf(columnType);
            String columnTypeName = rsMetaData.getColumnTypeName(i + 1);
            if ("bigserial".equals(columnTypeName)) {
                columnTypeName = "int8";
            }
            String columnLabel = rsMetaData.getColumnLabel(i + 1);
//            rsMetaData.
//                    System.out.println("columnLabel = " + columnLabel);
            System.out.println("columnName = " + columnName);
            System.out.println("columnTypeName = " + columnTypeName);

            System.out.println("=============");
        }
        return null;
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

    @SneakyThrows
    @Test
    public void test2() {
        HikariDataSource dataSource = getDataSource();
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();

        List<FieldInfo> fieldInfos = getFieldInfosBySql("public", connection, "select ods_asset_blood_lineage.*,ods_asset_data_asset.* from ods_asset_data_asset,ods_asset_blood_lineage where 1=2");
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
