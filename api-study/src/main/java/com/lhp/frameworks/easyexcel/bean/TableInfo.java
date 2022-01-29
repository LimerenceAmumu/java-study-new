package com.lhp.frameworks.easyexcel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lihp
 * @date 2021年11月11日 15:22
 */
@Data
public class TableInfo {
    @ExcelProperty(index=1)
    private String value;
}
