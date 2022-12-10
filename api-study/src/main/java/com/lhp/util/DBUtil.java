package com.lhp.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DBUtil {
    public static Object parseValue(Object value) {
        if (value instanceof java.sql.Date) {
            return new SimpleDateFormat("yyyy-MM-dd").format((Date) value);
        } else if (value instanceof Time) {
            return new SimpleDateFormat("HH:mm:ss").format((Date) value);
        } else if (value instanceof Timestamp) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) value);
        } else {
            return value;
        }
    }

    //获取数据库连接
    public Connection getConnection(String url, String username, String password, String driver) {
        try {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);
            return con;
        } catch (Exception e) {
            log.error(">>>jdbc getConnection error:", e);
            return null;
        }
    }

    public Map<String, Object> getOne(Connection connection, String sql) {
        return getOne(connection, sql, true);
    }

    public Map<String, Object> getOne(Connection connection, String sql, boolean closeConnection) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            Map<String, Object> map = queryRunner.query(connection, sql, new MapHandler());
            if (map != null) {
                Map<String, Object> targetMap = new LinkedHashMap<>();
                for (String key : map.keySet()) {
                    Object value = map.get(key);
                    targetMap.put(key, parseValue(value));
                }
                return targetMap;
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error(">>>sql[{}]查询失败:", sql, e);
            return null;
        } finally {
            if (closeConnection) {
                try {
                    DbUtils.close(connection);
                } catch (SQLException e) {
                    log.error(">>> close connection error:", e);
                }
            }
        }
    }

    public Long count(Connection connection, String sql) {
        return count(connection, sql, true);
    }

    public Long count(Connection connection, String sql, boolean closeConnection) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            Long count = queryRunner.query(connection, sql, new ScalarHandler<>());
            return count;
        } catch (SQLException e) {
            log.error(">>>sql[{}]查询失败:", sql, e);
            return 0L;
        } finally {
            if (closeConnection) {
                try {
                    DbUtils.close(connection);
                } catch (SQLException e) {
                    log.error(">>> close connection error:", e);
                }
            }
        }
    }

    public List<Map<String, Object>> listForMaps(Connection connection, String sql) {
        return listForMaps(connection, sql, true);
    }

    public List<Map<String, Object>> listForMaps(Connection connection, String sql, boolean closeConnection) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            List<Map<String, Object>> list = queryRunner.query(connection, sql, new MapListHandler());
            List<Map<String, Object>> resultList = list.stream().map(map -> {
                Map<String, Object> targetMap = new LinkedHashMap<>();
                for (String key : map.keySet()) {
                    Object value = map.get(key);
                    targetMap.put(key, parseValue(value));
                }
                return targetMap;
            }).collect(Collectors.toList());
            return resultList;
        } catch (SQLException e) {
            log.error(">>>sql[{}]查询失败:", sql, e);
            return new ArrayList<>();
        } finally {
            if (closeConnection) {
                try {
                    DbUtils.close(connection);
                } catch (SQLException e) {
                    log.error(">>> close connection error:", e);
                }
            }
        }
    }

    public <T> List<T> list(Connection connection, String sql, Class<T> beanClass) {
        return list(connection, sql, beanClass, true);
    }

    public <T> List<T> list(Connection connection, String sql, Class<T> beanClass, boolean closeConnection) {
        try {
            QueryRunner queryRunner = new QueryRunner();
            List<T> list = queryRunner.query(connection, sql, new BeanListHandler<>(beanClass));
            return list;
        } catch (SQLException e) {
            log.error(">>>sql[{}]查询失败:", sql, e);
            return new ArrayList<>();
        } finally {
            if (closeConnection) {
                try {
                    DbUtils.close(connection);
                } catch (SQLException e) {
                    log.error(">>> close connection error:", e);
                }
            }
        }
    }

    public boolean update(Connection connection, String sql) {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner();
            Integer update = queryRunner.execute(connection, sql);
            result = update != null;
            return true;
        } catch (SQLException e) {
            System.out.println("报错");
            e.printStackTrace();
            return false;
        } finally {
            if (result) {
                DbUtils.commitAndCloseQuietly(connection);
            } else {
                DbUtils.rollbackAndCloseQuietly(connection);
                throw new RuntimeException("break");
            }
        }
    }

    public boolean update(Connection connection, String sql, boolean autoCommit) {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner();
            Integer update = queryRunner.execute(connection, sql);
            result = update != null;
            return true;
        } catch (SQLException e) {
            log.error(">>>sql[{}]执行错误:", sql, e);
            return false;

        } finally {
            if (autoCommit) {
                if (result) {
                    DbUtils.commitAndCloseQuietly(connection);
                } else {
                    DbUtils.rollbackAndCloseQuietly(connection);
                }
            }
        }
    }

    public void close(Connection connection) {
        if (connection != null) {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                log.error(">>> close connection error:", e);
            }
        }
    }
}
