package com.lhp.stringDemo;


import lombok.Data;

/**
 * @author: wwj
 * @description: TODO
 * @date: 2022/11/7 16:16
 * @version: 1.0
 */
@Data
public class MappingFiledVO {

    /**
     * sourceFiled : name 目标名称
     * targetFiled : personName 目标字段
     * function : /path/fun2 函数
     * sourceFiledType: 源数据字段数据类型
     * targetFiledType： 目标字段数据类型
     * codeType： 编码规则
     */
    private String sourceFiled;
    private String targetFiled;
    private String function;
    private String sourceFieldJsonPath;
    private String sourceFiledType;
    private String targetFiledType;
}
