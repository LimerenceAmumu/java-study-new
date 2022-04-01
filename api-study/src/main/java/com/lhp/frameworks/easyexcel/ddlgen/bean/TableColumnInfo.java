package com.lhp.frameworks.easyexcel.ddlgen.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/3/31 16:11
 */
@Data
public class TableColumnInfo {
    @ExcelProperty(index = 0)
    String columnName;
    @ExcelProperty(index = 1)

    String columnType;
    @ExcelProperty(index = 2)

    String common;
    @ExcelProperty(index = 3)

    String hasParmary;
    @ExcelProperty(index = 4)

    String hasForeign;
    @ExcelProperty(index = 5)
    String hasUnique;
    @ExcelProperty(index = 6)
    String scene;
    @ExcelProperty(index = 7)
    String remark;
    @ExcelProperty(index = 8)
    String hasSequence;
    @ExcelProperty(index = 9)
    String hasNoNull;
}
