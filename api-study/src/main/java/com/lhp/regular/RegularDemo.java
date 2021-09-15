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

    /**
     * 校验数字
     */
    @Test
    public void test1() {
        Pattern compile = compile("^[0-9]*$");
        Matcher matcher = compile.matcher("12");
        boolean matches = matcher.matches();
        System.out.println("matches = " + matches);

        Boolean matcher1 = compile.matcher("1A").matches(); //fasle
        System.out.println("matcher1 = " + matcher1);

    }


}
