package com.lhp.frameworks.easyexcel.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {
    /**
     * 暂时存储data
     */
    private List<T> rows = new ArrayList<T>();
 
    /**
     * 解析每行都回调invoke()方法
     * @param object
     * @param context
     */
    @SneakyThrows
    @Override
    public void invoke(T object, AnalysisContext context) {
        rows.add(object);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
    }
 
    public List<T> getRows(){
        return rows;
    }
}
