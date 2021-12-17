package com.lhp.bean;

import lombok.Data;

import java.util.List;

@Data
public class AuditTaskJobInfoDTO {
    private List<String> columns;
    private List<String> tables;
    private String username;
    private String pwd;
    private String url;
    private Integer auditType;
}