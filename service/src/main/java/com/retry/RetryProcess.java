package com.retry;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 重试注解
 *
 * @author ly
 * @date 2021/7/28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RetryProcess {
    int maxCount() default 1;

    int sleepTime() default 1000;
}
