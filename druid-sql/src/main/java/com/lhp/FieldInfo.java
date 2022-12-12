package com.lhp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLDataException;
import java.time.LocalDateTime;

/**
 * @author : lihp
 * @Description :
 * @date : 2022/11/29 19:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FieldInfo {

    private String code;
    private String name;
    private boolean partitionKey;
    private String columnType;
    private Boolean isNullable;
    private String size;
    private String defaultValue;
    private Boolean isKey;
    private Boolean isChanged;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String buildColumnType(
            final String type,
            final String size,
            final String precision
    ) throws SQLDataException {
        if (size != null) {
            final int sizeInt;
            try {
                sizeInt = Integer.parseInt(size);
            } catch (final NumberFormatException nfe) {
                throw new SQLDataException("Size 字段不能被转换成 Integer", nfe);
            }

            final String baseType;
            final String afterMagnitude;
            final int unsignedIndex = StringUtils.indexOfIgnoreCase(type, "unsigned");
            if (unsignedIndex != -1) {
                baseType = (type.substring(0, unsignedIndex)).trim();
                afterMagnitude = type.substring(unsignedIndex);
            } else {
                baseType = type;
                afterMagnitude = null;
            }

            if (precision != null) {
                final int precisionInt;
                try {
                    precisionInt = Integer.parseInt(precision);
                } catch (final NumberFormatException nfe) {
                    throw new SQLDataException("Precision 字段不能被转换成 Integer", nfe);
                }
                return baseType
                        + '('
                        + sizeInt
                        + ", "
                        + precisionInt
                        + ')'
                        + (afterMagnitude != null ? ' ' + afterMagnitude : "");
            } else {
                return baseType
                        + '('
                        + sizeInt
                        + ')'
                        + (afterMagnitude != null ? ' ' + afterMagnitude : "");
            }
        } else {
            return type;
        }
    }
}
