package com.lhp.frameworks.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/8/11 14:24
 */
public class EasyExcelDynamicWrite {

    public List<List<Object>> genData() {
        List<List<Object>> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            List<Object> data = new ArrayList<>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }

    public List<List<String>> genTitle() {
        List<List<String>> list = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList<>();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList<>();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }


    /**
     * 动态添加 数据
     *
     * @throws IOException
     */
    @Test
    public void dynamicHeadWrite() throws IOException {
        File fileName = new File(System.getProperty("java.io.tmpdir"), "测试.xlsx");


        ExcelWriter excelWriter = EasyExcel.write(fileName)
                .head(genTitle())
                .build();
        // 这里注意 如果同一个sheet只要创建一次
        WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
        for (int i = 0; i < 500000; i++) {
            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据

            excelWriter.write(genData(), writeSheet);
        }
        excelWriter.finish();
    }
}
