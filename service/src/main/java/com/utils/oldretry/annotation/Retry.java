package com.utils.oldretry.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Boa
 */
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Retry {
    int maxCount();
    int sleepTime();




}
