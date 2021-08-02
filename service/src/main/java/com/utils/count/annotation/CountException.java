package com.utils.count.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * CountException注解
 *
 * @author ly
 * @date 2021/7/28
 */
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CountException {
    int maxCount() default 4;
    String methodName();

}
