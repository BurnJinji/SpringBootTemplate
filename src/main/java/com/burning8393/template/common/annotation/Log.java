package com.burning8393.template.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Log class
 *
 * @author : Pangxw
 * @date : 2019/2/27 15:41
 * @description : 日志注解类
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Log {
    /**
     * 日志描述， 这里使用了@AliasFor 别名，spring 提供的
     * @return
     */
    @AliasFor("desc")
    String value() default "";

    /**
     * 日志描述
     * @return
     */
    @AliasFor("value")
    String desc() default "";

    /**
     * 是否不记录日志
     * @return
     */
    boolean ignore() default false;
}
