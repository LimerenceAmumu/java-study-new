package com.lhp.frameworks.easyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lihp
 * @date 2021年11月11日 10:42
 */
@Data
public class DWModel  {
    @ExcelProperty(value = "英文字段名")
    private String enName;

    @ExcelProperty(value = "中文字段名")
    private String cnName;

    @ExcelProperty(value = "字段类型")
    private String columntype;

    @ExcelProperty(value = "默认值")
    private String deValue;

    @ExcelProperty(value = "处理规则")
    private String rule;


}
