package com.lhp;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**+
 * 数据元标准状态枚举类
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DataStandMetaStatusEnum {
    ONLINE("on", "现行"),
    INSTEAD("instead", "被替代"),
    OFF("off", "废止"),
    ;
    private String code;
    private String desc;

    //状态：online：  现行 instead：被替代 off：  废止';


    public static String getEnumDesc(String code) {

        DataStandMetaStatusEnum[] values = values();
        for (DataStandMetaStatusEnum cur : values) {
            if (Objects.equals(code, cur.getCode())) {
                return cur.getDesc();
            }
        }
        return "";
    }

    public static String getEnumCode(String desc) {
        if (StringUtils.isEmpty(desc)) {
            return "";
        }
        DataStandMetaStatusEnum[] values = values();
        for (DataStandMetaStatusEnum cur : values) {
            if (Objects.equals(desc.trim(), cur.getDesc())) {
                return cur.getCode();
            }
        }
        return "";
    }
    DataStandMetaStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
