package com.lhp.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/7/28 10:48
 */
public class JdbcClient {
    final static   String pgurl="jdbc:postgresql://192.168.200.166:5432/test";
    final static   String pgusername="lakerw";
    final static   String pwd="6F+LUnsEI49eFFSf";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(pgurl, pgusername, pwd);

        DatabaseMetaData metaData = connection.getMetaData();
    }
}
