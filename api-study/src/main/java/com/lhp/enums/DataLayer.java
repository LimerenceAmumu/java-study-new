package com.lhp.enums;

import java.util.Locale;
import java.util.Objects;

/**
 * 数据分层
 *
 * @author Xiao Yingjun
 */
public enum DataLayer {
    /**
     * 原始数据层
     */
    STG,
    /**
     * 数据标准层
     */
    ODS,

    /**
     * 数据明细层
     */
    DWD,

    /**
     * 数据汇总层
     */
    DWS,

    /**
     * 数据集市层
     */
    ADS;


    public static DataLayer getDataLayerByStr(String datalayer) {
        datalayer = datalayer.toUpperCase(Locale.ROOT);
        DataLayer[] values = values();
        for (DataLayer value : values) {
            if (Objects.equals(datalayer, value.toString())) {
                return value;
            }
        }
        return STG;

    }
}
