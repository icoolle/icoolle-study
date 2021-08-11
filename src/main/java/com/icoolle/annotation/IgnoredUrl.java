package com.icoolle.annotation;

import java.lang.annotation.*;

/**
 * 忽略请求地址
 * @author
 */
@Retention(RetentionPolicy.RUNTIME)//元注解，定义注解被保留策略，一般有三种策略
@Target(ElementType.TYPE) //定义了注解声明在哪些元素之前使用
@Documented
@Inherited
public @interface IgnoredUrl {

    String[] urls() default {};
}
