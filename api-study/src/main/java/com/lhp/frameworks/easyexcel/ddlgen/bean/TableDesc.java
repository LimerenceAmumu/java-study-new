package com.lhp.frameworks.easyexcel.ddlgen.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: 表描述
 * @author: lihp
 * @date: 2022/3/31 16:08
 */
@Data
public class TableDesc {
    @ExcelProperty(index = 1)
    String value;
}
