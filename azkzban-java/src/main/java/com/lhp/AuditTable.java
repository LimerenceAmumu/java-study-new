package com.lhp;

import com.google.common.collect.Lists;

import com.lhp.bean.AuditTaskJobInfoDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

/**
 * @author lihp
 * @date 2021年11月25日 16:58
 */

public class AuditTable {
    public static void main(String[] args) {


        //Job 信息
        AuditTaskJobInfoDTO auditTaskJobInfoDTO = extractJobInfo(args);

        //判断稽核类型


    }

    public static Connection getConnection(AuditTaskJobInfoDTO auditTaskJobInfoDTO) {
        Connection connection = null;
        return connection;
    }

    /**
     * 抽取 参数 封装为对象
     * <p>
     * // {auditType:0,       0
     * columns:[ccc,ddww],     1
     * pwd:999999,                2
     * tables:[ccc,ddww],           3
     * url:jdbc:mysql://192.168.110.220:3306/standard-investment?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai,  4
     * username:火石}        5
     *
     * @return
     */
    public static AuditTaskJobInfoDTO extractJobInfo(String[] args) {
        AuditTaskJobInfoDTO auditTaskJobInfoDTO = new AuditTaskJobInfoDTO();
        auditTaskJobInfoDTO.setColumns(Arrays.asList(args[1].split(",")));
        auditTaskJobInfoDTO.setTables(Arrays.asList(args[3].split(",")));
        auditTaskJobInfoDTO.setUsername(args[5]);
        auditTaskJobInfoDTO.setPwd(args[2]);
        auditTaskJobInfoDTO.setUrl(args[4]);
        auditTaskJobInfoDTO.setAuditType(Integer.parseInt(args[0]));
        return auditTaskJobInfoDTO;
    }
}
