package com.lhp.frameworks.easyexcel.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihp
 * @date 2021年11月11日 15:35
 */
public class TableBuzInfoExcelListener<T> extends AnalysisEventListener<T> {
    /**
     * 暂时存储data
     */
    private List<T> rows = new ArrayList<T>();

    /**
     * 解析每行都回调invoke()方法
     * @param object
     * @param context
     */
    @Override
    public void invoke(T object, AnalysisContext context) {
        if(rows.size()>=4){

        }

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
