package com.lhp.regular;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @Description:
 * @author: lihp
 * @date: 2021/9/14 4:02 下午
 */
public class RegularDemo {
    private static final Pattern GET_PATTERN = compile("get(\\p{javaUpperCase}\\w*)");
    public static final String REGEX_NUM = "^[0-9]*$";//校验数字
    public static final String REGEX_DECIMAL = "^(()|[-,+])(([1-9]{1}\\d*))(\\.(\\d){1,2})?$";//校验小数  带1-2 位的小数或者负数
    public static final String REGEX_CHINESE = "^[\\u4e00-\\u9fa5]*$";//校验中文
    public static final String REGEX_EN_AND_NUM = "^[A-Za-z0-9]+$";//校验英文和数字
    public static final String REGEX_EN = "^[A-Za-z]+$";//校验英文
    public static final String REGEX_EN_LOWER = "^[a-z]+$";//校验小写英文
    public static final String REGEX_26EN_NUM = "^[A-Za-z0-9]+$";//数字和26个英文
    public static final String REGEX_EN_NUM_UNDERLINE = "^\\w+$";//数字英文下划线

    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";//email
    public static final String REGEX_DOMAIN = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";//域名
    public static final String REGEX_INTERNETURL = "[a-zA-z]+://[^\\s]* 或 ^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";//url
    public static final String REGEX_PHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|4|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";//手机号码
    public static final String REGEX_TEL_PHONE = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";//电话号码
    public static final String REGEX_TEL_PHONE_NATIONAL = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";//国内电话号码

    public static final String REGEX_ID_CARD = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";//身份证号


    public static final String REGEX_DATE = "^\\d{4}-\\d{1,2}-\\d{1,2}";//日期格式
    public static final String REGEX_MONEY = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,2})?$";//货币
    public static final String REGEX_CHINESE_CHAR = "[\\u4e00-\\u9fa5]";//中文字符
    public static final String REGEX_BLANK = "\\n\\s*\\r";//空白行的正则表达式
    public static final String REGEX_QQ = "[1-9][0-9]{4,}";//腾讯QQ

    public static final String REGEX_POST_CODE = "[1-9]\\d{5}(?!\\d)";//邮政编码
    public static final String REGEX_IP = "[1-9][0-9]{4,}";//IP
    public static final String REGEX_IPV4 = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";//IPV4


    public static boolean test(Pattern pattern, String arg) {
        Matcher m = pattern.matcher(arg);
        return m.matches();
    }

    public static void checkRegex(RuleInnerTypeEnum regex, String val) {
        Pattern compile = compile(regex.getRegex());
        Matcher matcher = compile.matcher(val);
        System.out.println(regex.getName() + "校验" + matcher.matches());
    }

    @Test
    public void testUtil() {
//        Pattern compile = compile(REGEX_CHINESE);
//        Matcher asdas = compile.matcher("asdas");
//        System.out.println(asdas.matches());
//
//        Matcher sss = compile.matcher("你");
//        System.out.println(sss.matches());


        System.out.println(REGEX_BLANK);
    }

    @Test
    public void testEmail() {
        System.out.println(testutil(REGEX_CHINESE, "你好"));

    }

    private boolean testutil(String reg, String arg) {
        Matcher m = Pattern.compile(reg).matcher(arg);
        return m.matches();
    }

    /**
     * 校验数字
     */
    @Test
    public void testNum() {
        Pattern compile = compile("^[0-9]*$");
        Matcher matcher = compile.matcher("12");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);

        Boolean matcher1 = compile.matcher("1A").matches(); //fasle
        System.out.println("matcher1 = " + matcher1);

    }

    /**
     * 数字字母下划线
     */
    @Test
    public void test6() {
        String input = "545Aasd_";
        String regex = "^\\w+$";
        Matcher m = Pattern.compile(regex).matcher(input);
        System.out.println(m.find());
    }

    // 匹配中文
    @Test
    public void a1() {
        //String input ="nihc你你憨憨";//false
        String input = "你你憨繁体字冫奤奤憨";//false
        String regex = "^[\\u4e00-\\u9fa5]*$";
        Matcher m = Pattern.compile(regex).matcher(input);
        System.out.println(m.find());
    }

    @Test
    public void test2() {
        Pattern compile = compile("^table(.)*");
        Matcher matcher = compile.matcher("tableAAA");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);


    }


    @Test
    public void test3() {
        Pattern compile = compile("^table");
        Matcher matcher = compile.matcher("tablePPP");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);


    }

    /**
     * 校验日期
     */
    @Test
    public void testDate() {
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";

        Pattern compile = compile(regex);
        Matcher matcher = compile.matcher("2021-10-20");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);

    }
    //        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){2})?$"); // 判断小数点后2位的数字的正则表达式

    /**
     * 金额
     */
    @Test
    public void testMoney() {
        String regex = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){2})?$";
        Pattern pattern = compile(regex); // 判断小数点后2位的数字的正则表达式

        Matcher matcher = pattern.matcher("9595665656.00");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);
    }

    /**
     * 数字字母
     */
    @Test
    public void testNumAndEn() {
        String input = "545Aasd";
        String regex = "^[A-Za-z0-9]+$";
        Matcher m = Pattern.compile(regex).matcher(input);
        System.out.println(m.find());
    }

    @Test
    public void testDecimal() {
        String regex = "^(()|[-,+])(([1-9]{1}\\d*))(\\.(\\d){1,2})?$";
        Pattern pattern = compile(regex); // 判断小数点后2位的数字的正则表达式

        Matcher matcher = pattern.matcher("-9595665656.00");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);
    }

    /**
     *
     */
    @Test
    public void testIdCard() {
        boolean idNumber = isIDNumber("4201 2319 9705 22605X");
        System.out.println("idNumber = " + idNumber);
    }

    /**
     * 手机号校验
     */
    @Test
    public void testPhone() {
        boolean phoneLegal = PhoneFormatCheckUtils.isPhoneLegal("12183210600");
        System.out.println("phoneLegal = " + phoneLegal);
    }

    /**
     * 身份证号校验
     *
     * @param IDNumber
     * @return
     */
    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return matches;
    }

    /**
     * 正则判断输入字符是否是以逗号间隔
     */
    @Test
    public void testList() {
        String regex = "^\\w+(,\\w+)*$";
        Pattern pattern = compile(regex);

        Matcher matcher = pattern.matcher("asd,ddd,52,&&");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);
    }

    @Test
    public void testEnum() {

        checkRegex(RuleInnerTypeEnum.REGEX_TEL_PHONE, "320-12569857");
        checkRegex(RuleInnerTypeEnum.REGEX_TEL_PHONE, "aaa ");


        //IP v4
        checkRegex(RuleInnerTypeEnum.REGEX_IPV4, "192.36.3sss6.33");
        checkRegex(RuleInnerTypeEnum.REGEX_IPV4, "192.36.63.33");

        //
        checkRegex(RuleInnerTypeEnum.REGEX_IPV6, "fe80:0000:0000:0000:0204:61ff:fe9d:f156");
        checkRegex(RuleInnerTypeEnum.REGEX_IPV6, "fe80:0000:0000:0000:0204:61ff:fe9d:f156AAAA");

        // TODO 需要重新找
        checkRegex(RuleInnerTypeEnum.REGEX_BLANK, "fe80:0000:0000:0000:0204:61ff:fe9d:f156");
        checkRegex(RuleInnerTypeEnum.REGEX_BLANK, " ");

        //
        checkRegex(RuleInnerTypeEnum.REGEX_DATE, "2012-12-20");
        checkRegex(RuleInnerTypeEnum.REGEX_DATE, " ");


        //
        checkRegex(RuleInnerTypeEnum.REGEX_TEL_PHONE_NATIONAL, "0511-4405222");
        checkRegex(RuleInnerTypeEnum.REGEX_TEL_PHONE_NATIONAL, "aaa ");

        checkRegex(RuleInnerTypeEnum.REGEX_PHONE, "13566663320");
        checkRegex(RuleInnerTypeEnum.REGEX_PHONE, "aaa ");

        checkRegex(RuleInnerTypeEnum.REGEX_TEL_PHONE, "320-12569857");
        checkRegex(RuleInnerTypeEnum.REGEX_TEL_PHONE, "aaa ");

        checkRegex(RuleInnerTypeEnum.REGEX_INTERNETURL, "https://www.bing.com/");
        checkRegex(RuleInnerTypeEnum.REGEX_INTERNETURL, "https://www.bing.com/asdaqwe*/*&&^ ");

        checkRegex(RuleInnerTypeEnum.REGEX_EN_NUM_UNDERLINE, "asd656_");
        checkRegex(RuleInnerTypeEnum.REGEX_EN_NUM_UNDERLINE, "https://www.bing.com/asdaqwe*/*&&^ ");

        checkRegex(RuleInnerTypeEnum.REGEX_ID_CARD, "41112319970522605X");
        checkRegex(RuleInnerTypeEnum.REGEX_ID_CARD, "https://www.bing.com/asdaqwe*/*&&^ ");


    }


}
