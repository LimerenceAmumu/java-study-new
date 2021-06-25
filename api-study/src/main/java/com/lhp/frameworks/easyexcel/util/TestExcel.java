package com.lhp.frameworks.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.lhp.bean.DemoExcelBean;
import org.junit.Test;

import java.util.List;

/**
 * @author Amumu
 * @create 2020/6/3 16:36
 */
public class TestExcel {
    @Test
    public void testExcelimpot(){

        String fileName="C:\\Users\\Limer\\Desktop\\手工标签绑定数据资源导入模板.xlsx";
        ExcelListener<DemoExcelBean> del = new ExcelListener<>();
        ExcelReader reader=null;

        ExcelReader excelReader = EasyExcel.read(fileName).build();
        ReadSheet readSheet1 =
                EasyExcel.readSheet(1).head(DemoExcelBean.class).registerReadListener(del).build();
        excelReader.read(readSheet1,readSheet1);
        List<DemoExcelBean> rows = del.getRows();
        System.out.println("rows = " + rows);
    }
}
