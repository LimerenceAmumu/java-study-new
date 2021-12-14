package com.lhp;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import com.lhp.bean.AuditTaskJobInfoDTO;

/**
 * @author lihp
 * @date 2021年11月25日 18:13
 */
public class TestClient {
    public static void main(String[] args) {

        AuditTaskJobInfoDTO auditTaskJobInfoDTO = new AuditTaskJobInfoDTO();
        auditTaskJobInfoDTO.setColumns(Lists.newArrayList("ccc","ddww"));
        auditTaskJobInfoDTO.setTables(Lists.newArrayList("ccc","ddww"));
        auditTaskJobInfoDTO.setUsername("火石");
        auditTaskJobInfoDTO.setPwd("999999");
        auditTaskJobInfoDTO.setUrl("jdbc:mysql://192.168.110.220:3306/standard-investment?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai");
        auditTaskJobInfoDTO.setAuditType(0);

        String s = JSON.toJSONString(auditTaskJobInfoDTO);
        System.out.println("s = " + s);

    }
}
