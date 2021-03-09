package com.lhp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Amumu
 * @create 2021/3/9 21:33
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String defaultValue() default "default value";

    String value() default "default value";

}
