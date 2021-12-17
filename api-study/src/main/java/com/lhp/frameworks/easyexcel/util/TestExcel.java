package com.lhp.frameworks.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.lhp.bean.DemoExcelBean;
import com.lhp.frameworks.easyexcel.bean.DWModel;
import com.lhp.frameworks.easyexcel.bean.TableInfo;
import javafx.scene.control.Tab;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Amumu
 * @create 2020/6/3 16:36
 */
public class TestExcel {
    @Test
    public void testExcelimpot() {

        String fileName = "C:\\Users\\Limer\\Desktop\\手工标签绑定数据资源导入模板.xlsx";
        ExcelListener<DemoExcelBean> del = new ExcelListener<>();
        ExcelReader reader = null;

        ExcelReader excelReader = EasyExcel.read(fileName).build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();


        ReadSheet readSheet1 =
                EasyExcel.readSheet(1).head(DemoExcelBean.class).registerReadListener(del).build();
        excelReader.read(readSheet1, readSheet1);
        List<DemoExcelBean> rows = del.getRows();
        System.out.println("rows = " + rows);
    }

    /**
     * easyExcel 获取所有的sheet页
     */
    @Test
    public void testGetExcelSheet() {

        String fileName = "D:\\IdeaCode\\java-study\\api-study\\src\\main\\java\\com\\lhp\\frameworks\\easyexcel\\util\\demo.xlsx";
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        System.out.println(sheets);

    }

    /**
     * 测试读取demo中的数据
     */
    @SneakyThrows
    @Test
    public void testReadDemo() {
        //1. 加载文件
        String fileName = "D:\\IdeaCode\\java-study\\api-study\\src\\main\\java\\com\\lhp\\frameworks\\easyexcel\\util\\demo.xlsx";
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        //2.实现并创建监听器
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();

        ArrayList<DWModel> dwModels = new ArrayList<>();
        ArrayList<String> sqls = new ArrayList<>();
        for (ReadSheet sheet : sheets) {
            if(Objects.equals(sheet.getSheetName(),"同步规则")) {
                continue;
            }
            ExcelListener<TableInfo> tableInfoExcelListener = new ExcelListener<>();
            EasyExcel.read(new FileInputStream(file), TableInfo.class,tableInfoExcelListener).sheet(sheet.getSheetName()).headRowNumber(0).doRead();
            List<TableInfo> rows = tableInfoExcelListener.getRows();
            List<TableInfo> tableInfos = rows.subList(0, 5);

            ExcelListener<DWModel> dwModelExcelListener = new ExcelListener<>();
            EasyExcel.read(new FileInputStream(file), DWModel.class, dwModelExcelListener).sheet(sheet.getSheetName()).headRowNumber(8).doRead();
            List<DWModel> metaDatas = dwModelExcelListener.getRows();
            dwModels.addAll(metaDatas);
        }
        System.out.println("dwModels = " + dwModels);

    }
    public String generateSql(List<DWModel> metaDatas,List<TableInfo> tableInfos){
        StringBuffer sqlbuffer = new StringBuffer();

                sqlbuffer.append("create table ods_datahouse_dict");
        for (DWModel metaData : metaDatas) {

        }

        return "";
    }

    @SneakyThrows
    public static void main(String[] args) {

        //1. 加载文件
        String fileName = "D:\\IdeaCode\\java-study\\api-study\\src\\main\\java\\com\\lhp\\frameworks\\easyexcel\\util\\demo.xlsx";
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        //2.实现并创建监听器
        ExcelReader excelReader = EasyExcel.read(fileName).build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();

        ArrayList<DWModel> dwModels = new ArrayList<>();
        ArrayList<String> sqls = new ArrayList<>();
        for (ReadSheet sheet : sheets) {
            if(Objects.equals(sheet.getSheetName(),"同步规则")) {
                continue;
            }
            ExcelListener<TableInfo> tableInfoExcelListener = new ExcelListener<>();
            EasyExcel.read(new FileInputStream(file), TableInfo.class,tableInfoExcelListener).sheet(sheet.getSheetName()).headRowNumber(0).doRead();
            List<TableInfo> rows = tableInfoExcelListener.getRows();
            List<TableInfo> tableInfos = rows.subList(0, 5);

            ExcelListener<DWModel> dwModelExcelListener = new ExcelListener<>();
            EasyExcel.read(new FileInputStream(file), DWModel.class, dwModelExcelListener).sheet(sheet.getSheetName()).headRowNumber(8).doRead();
            List<DWModel> metaDatas = dwModelExcelListener.getRows();
            dwModels.addAll(metaDatas);
        }
        System.out.println("dwModels = " + dwModels);
    }
}
