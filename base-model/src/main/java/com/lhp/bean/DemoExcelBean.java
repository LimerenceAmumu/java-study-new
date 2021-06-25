package com.lhp.bean;


import lombok.Data;

/**
 * @author Amumu
 * @create 2020/6/3 15:46
 */
@Data
public class DemoExcelBean {
    private String sysName;
    private String dbUser;
    private String tableName;
    private String chName;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }
}
