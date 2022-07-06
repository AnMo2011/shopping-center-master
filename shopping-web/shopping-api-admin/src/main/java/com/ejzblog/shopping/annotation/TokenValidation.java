package com.ejzblog.shopping.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.ejzblog.shopping.constant.Constant.TOKEN;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-07-06 14:06
 * @see com.ejzblog.shopping.annotation
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenValidation {

    String value() default TOKEN;

    /**
     * 验证类型
     * <ul>
     *     <ui>TRUE：验证</ui>
     *     <ui>FALSE：不验证</ui>
     * </ul>
     */
    boolean validationType() default true;

}