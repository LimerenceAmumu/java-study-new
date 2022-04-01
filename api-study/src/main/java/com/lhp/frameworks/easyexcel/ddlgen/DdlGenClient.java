package com.lhp.frameworks.easyexcel.ddlgen;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.lhp.frameworks.easyexcel.ddlgen.bean.TableColumnInfo;
import com.lhp.frameworks.easyexcel.ddlgen.bean.TableDesc;
import com.lhp.frameworks.easyexcel.util.ExcelListener;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @author: lihp
 * @date: 2022/3/31 16:06
 */
@Slf4j
public class DdlGenClient {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/Users/lihepeng/IdeaProjects/java-study/api-study/src/main/java/com/lhp/frameworks/easyexcel/ddlgen/表结构0331.xlsx");
        //获取所有的sheet
        ExcelReader excelReader = EasyExcel.read(new FileInputStream(file)).build();
        List<ReadSheet> sheets = excelReader.excelExecutor().sheetList();
        for (ReadSheet sheet : sheets) {
            // 用于读取 Excel顶部信息
            /**
             * 表名	        ods_pub_domestic_special_purpose_cosmetics
             * 表说明	    成果信息-国产特殊用途化妆品表
             * 主键	        ds_id
             * 来源表	    ds_domestic_special_purpose_cosmetics_4235
             */

            ExcelListener<TableDesc> tableInfoExcelListener = new ExcelListener<>();
            EasyExcel.read(file, TableDesc.class, tableInfoExcelListener).sheet(sheet.getSheetName()).headRowNumber(0).doRead();
            List<TableDesc> dataList = tableInfoExcelListener.getRows();
            if (CollectionUtils.isEmpty(dataList)) {
                break;
            }
            dataList = dataList.subList(0, 5);//截取前4行数据
            String tableName = dataList.get(0).getValue();
            String tableCommon = dataList.get(1).getValue();


            // 读取excel column 数据

            ExcelListener<TableColumnInfo> dictValueExcelListener = new ExcelListener<>();
            EasyExcel.read(file, TableColumnInfo.class, dictValueExcelListener).sheet(sheet.getSheetName()).headRowNumber(8).doRead();
            List<TableColumnInfo> columnInfoList = dictValueExcelListener.getRows();

            System.out.println("columnInfoList = " + columnInfoList);

            genDDl(tableName, tableCommon, columnInfoList);

            System.out.println("-----------------------------------------------------------------------------------------------");
        }


    }

    /**
     * 生成DDL
     *
     * @param tableName
     * @param tableCommon
     * @param columnInfoList
     */
    private static void genDDl(String tableName, String tableCommon, List<TableColumnInfo> columnInfoList) {


        StringBuilder columnDemotail = new StringBuilder("create table ")
                .append(tableName)
                .append("( ");

//create unique index ods_audit_rule_rule_name_uindex
//	on ods_audit_rule (rule_name);

        //生成 字段信息

        List<String> pkColumnNames = columnInfoList.stream()
                .filter(tableColumn -> Objects.equals(tableColumn.getHasParmary(), "1"))
                .map(TableColumnInfo::getColumnName).collect(Collectors.toList());

        int i = 0;
        for (TableColumnInfo tableColumnInfo : columnInfoList) {
            columnDemotail.append("\n");
            String columnName = tableColumnInfo.getColumnName();
            columnDemotail.append(columnName)
                    .append(StrUtil.SPACE)
                    .append(tableColumnInfo.getColumnType());

            if (Objects.equals(tableColumnInfo.getHasNoNull(), "1")) {
                columnDemotail.append(" NOT NULL ");
            }


            //最后一个字段解析完
            if (i == columnInfoList.size() - 1) {
                if (CollectionUtil.isNotEmpty(pkColumnNames)) {
                    columnDemotail.append(",").append("\n");
                    columnDemotail.append(StrUtil.SPACE)
                            .append(MessageFormat.format("CONSTRAINT \"{0}_pkey\" PRIMARY KEY (\"{1}\")", tableName.trim(), StrUtil.join("\",\"", pkColumnNames)))
                            .append("\n");
                }
            } else {
                columnDemotail.append(",").append("\n");
            }
            i++;
        }
        columnDemotail.append(");").append("\n");
        // 唯一约束

        // 注释信息
        //comment on table ods_audit_history_info is '稽核历史信息';
        //
        //comment on column ods_audit_history_info.audit_table_name is '表名';
        //表注释
        columnDemotail.append(" comment on table ")
                .append(tableName)
                .append(" is ")
                .append(StrUtil.wrap(tableCommon, "'", "';"))
                .append("\n");

        // 字段注释
        for (TableColumnInfo tableColumnInfo : columnInfoList) {
            if (StrUtil.isBlank(tableColumnInfo.getCommon())) {
                tableColumnInfo.setCommon(tableColumnInfo.getColumnName());
            }
            columnDemotail.append(" comment on column ")
                    .append(tableName + "." + tableColumnInfo.getColumnName())
                    .append(" is ")
                    .append(StrUtil.wrap(tableColumnInfo.getCommon(), "'", "';"))
                    .append("\n");
        }


        String ddlStr = columnDemotail.toString();
        System.out.println(ddlStr);
        FileUtil.writeString(ddlStr, new File("genDDL/" + tableName + ".sql"), "utf-8");
    }
}
