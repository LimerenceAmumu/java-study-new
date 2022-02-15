package com.lhp.regular;

import java.util.Objects;

/**
 * @Description: 内置规则编码枚举
 * @author: lihp
 * @date: 2021/12/27 15:58
 */
public enum RuleInnerTypeEnum {

    // 增加 TABLE_MIN

    //表行数最小值
    TABLE_MIN("TABLE_MIN", "rule", "", 1, 10, "table_rows_min_value", "校验表行数的最小值", "表行数最小值"),
    DICT("DICT", "dict", "", 4, 20, "check_data_dictionary", "校验字段内容是否在数据字典的范围内", "字典值校验"),
    // 空值
    BLANK("BLANK", "rule", "", 1, 20, "check_null_value", "校验字段内容否为null", "空值校验"),


    //最大值
    MAX("MAX", "rule", "", 2, 20, "check_max_value", "校验字段内容的最大值", "最大值校验"),
    MIN("MIN", "rule", "", 2, 10, "check_min_value", "校验表行数的最小值", "表行数最小值"),

    //范围
    RANGE("RANGE", "rule", "", 2, 20, "check_numerical_range", "校验字段内容的数字范围", "数值范围校验"),


    //长度
    LENGTH("LENGTH", "rule", "", 2, 20, "check_strlen", "校验字段内容的长度" +
            "", "字段长度校验" +
            ""),
    //重复值
    REPEAT("REPEAT", "rule", "", 2, 20, "check_null_value", "校验字段内容是否重复", "重复值校验"),
    //数字格式校验
    REGEX_NUM("REGEX_NUM", "regex", "^[0-9]*$", 2, 20, "check_digital_format", "校验字段内容是否是数字格式", "数字格式校验"),

    //校验小数  带1-2 位的小数或者负数
    REGEX_DECIMAL("REGEX_DECIMAL", "regex", "^(()|[-,+])(([1-9]{1}\\d*))(\\.(\\d){1,2})?$", 2, 20, "check_num_with_1-2_decimal_places", "校验字段内容是否是带1-2位小数的正数或负数", "带1-2位小数的正数或负数"),
    //校验中文
    REGEX_CHINESE("REGEX_CHINESE", "regex", "^[\\\u4e00-\\\u9fa5]*$", 2, 20, "check_Chinese_character_format"
            , "校验字段内容是否是汉字格式"
            , "汉字格式校验"
    ),
    //校验英文和数字
    REGEX_EN_AND_NUM("REGEX_EN_AND_NUM", "regex", "^[A-Za-z0-9]+$", 2, 20, "check_English_and_digital_format"
            , "校验字段内容是否是英文和数字格式"
            , "英文和数字格式校验"
    ),
    //由26个英文字母组成的字符串
    REGEX_EN("REGEX_EN", "regex", "^[A-Za-z]+$", 2, 20, "check_a_string_of_26_English_letters"
            , "校验字段内容是否是由26个英文字母组成的字符串"
            , "由26个英文字母组成的字符串"
    ),

    //由26个小写英文字母组成的字符串
    REGEX_EN_LOWER("REGEX_EN_LOWER", "regex", "^[a-z]+$", 2, 20, "check_a_string_of_26_lowercase_English_letters", "校验字段内容是否是由26个小写英文字母组成的字符串", "由26个小写英文字母组成的字符串"),


    //数字和26个英文
    REGEX_26EN_NUM("REGEX_26EN_NUM", "regex", "^[A-Za-z0-9]+$", 2, 20, "check_a_string_of_26_English_letters_and_numbers", "校验字段内容是否是由数字和26个英文字母组成的字符串", "由数字和26个英文字母组成的字符串"),
    //
    //数字英文下划线
    REGEX_EN_NUM_UNDERLINE("REGEX_EN_NUM_UNDERLINE", "regex", "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", 2, 20, "check_a_string_of_numbers_ 26_English_letters_or_underscores", "校验字段内容是否是由数字、26个英文字母或者下划线组成的字符串", "由数字、26个英文字母或者下划线组成的字符串"),


    //email
    REGEX_EMAIL("REGEX_EMAIL", "regex", "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", 2, 20, "check_email_format", "校验字段内容是否是Email地址", "Email地址校验"),


    //域名
    REGEX_DOMAIN("REGEX_DOMAIN", "regex", "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?", 2, 20, "check_realm_name", "校验字段内容是否是域名\n" +
            "\n", "域名校验"),


    //URL
    REGEX_INTERNETURL("REGEX_INTERNETURL", "regex", "[a-zA-z]+://[^\\s]*", 2, 20, "check_url", "校验字段内容是否是URL\n" +
            "\n\n" +
            "\n", "URL校验\n" +
            "\n"),
    //手机号码
    REGEX_PHONE("REGEX_PHONE", "regex", "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", 2, 20, "check_mobile", "校验字段内容是否是手机号码\n" +
            "\n", "手机号码校验\n" +
            "\n"),
    //电话号码
    REGEX_TEL_PHONE("REGEX_TEL_PHONE", "regex", "\\d{3}-\\d{7}|\\d{4}-\\d{8}|\\d{3}-\\d{8}|(\\d{7,8})", 2, 20, "check_telephone", "校验字段内容是否是电话号码\n" +
            "\n", "电话号码校验\n" +
            "\n"),

    //
    REGEX_TEL_PHONE_NATIONAL("REGEX_TEL_PHONE_NATIONAL", "regex", "\\d{3}-\\d{8}|\\d{4}-\\d{7}", 2, 20, "check_domestic_telephone\n" +
            "\n", "校验字段内容是否是国内电话号码\n" +
            "\n", "国内电话号码校验"),
    //身份证号
    REGEX_ID_CARD("REGEX_ID_CARD", "regex", "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)", 2, 20, "check_id", "校验字段内容是否是15或18位身份证号\n" +
            "\n", "身份证号校验\n" +
            "\n"),
    //日期格式
    REGEX_DATE("REGEX_DATE", "regex", "^\\d{4}-\\d{1,2}-\\d{1,2}", 2, 20, "check_date_format", "校验字段内容是否是日期格式\n" +
            "\n", "日期格式校验\n" +
            "\n"),
    //货币
    REGEX_MONEY("REGEX_MONEY", "regex", "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$", 2, 20, "check_currency_format", "校验字段内容是否是货币格式\n" +
            "\n", "货币格式校验\n" +
            "\n"),
    //中文字符
    //  REGEX_CHINESE_CHAR("REGEX_CHINESE_CHAR", "regex", "[\\u4e00-\\u9fa5]",2,20, "check_numerical_range","校验字段内容的数字范围","数值范围校验"),
    //空白行的正则表达式
    REGEX_BLANK("REGEX_BLANK", "regex", "^(\\s*)\\n", 2, 20, "check_blank", "校验字段内容是否是空白行\n" +
            "\n", "空白行校验"),
    //腾讯QQ
    REGEX_QQ("REGEX_QQ", "regex", "[1-9][0-9]{4,}", 2, 20, "check_qq", "校验字段内容是否是腾讯QQ号\n" +
            "\n", "腾讯QQ号校验\n" +
            "\n"),
    //邮政编码
    REGEX_POST_CODE("REGEX_POST_CODE", "regex", "[1-9]\\d{5}(?!\\d)", 2, 20, "check_postal_code", "校验字段内容是否是中国邮政编码\n" +
            "\n", "中国邮政编码校验\n" +
            "\n"),
    REGEX_IP("REGEX_IP", "regex", "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}", 2, 20, "check_ip", "校验字段内容是否是IP地址\n" +
            "\n", "IP地址校验\n" +
            "\n"),
    REGEX_IPV4("REGEX_IPV4", "regex", "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}", 2, 20, "check_ip_v4\n" +
            "\n", "校验字段内容是否是IP地址-v4\n" +
            "\n", "IP-v4地址校验\n" +
            "\n"),

    REGEX_IPV6("REGEX_IPV6", "regex", "^\\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)){3}))|:)))(%.+)?\\s*$", 2, 20, "check_ip_v6\n" +
            "\n", "校验字段内容是否是IP地址-v6\n" +
            "\n", "IP-v6地址校验\n" +
            "\n");


//
//    //表大小
//    TABLE_SIZE("TABLE_SIZE", "rule", "",2,20, "check_numerical_range","校验字段内容的数字范围","数值范围校验"),
//    //表行数
//    TABLE_COLUMN("TABLE_COLUMN", "rule", "",2,20, "check_numerical_range","校验字段内容的数字范围","数值范围校验");


    private String code;//编码
    private String type;//类型  regex dict  rule
    private String regex;// 正则表达式（type=regex 才用得到）
    private Integer auditType;
    private Integer ruleType;
    private String enName;
    private String name;
    private String desc;


//    RuleInnerTypeEnum(String code, String type, String regex,) {
//        this.code = code;
//        this.type = type;
//        this.regex = regex;
//    }


    RuleInnerTypeEnum(String code, String type, String regex, Integer auditType, Integer ruleType, String enName, String desc, String name) {
        this.code = code;
        this.type = type;
        this.regex = regex;
        this.auditType = auditType;
        this.ruleType = ruleType;
        this.enName = enName;
        this.desc = desc;
        this.name = name;
    }

    public static RuleInnerTypeEnum getEnumByCode(String code) {
        RuleInnerTypeEnum[] values = values();
        for (RuleInnerTypeEnum value : values) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getRegex() {
        return regex;
    }

    public Integer getAuditType() {
        return auditType;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public String getEnName() {
        return enName;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
