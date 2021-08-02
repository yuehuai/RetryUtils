package com.utils.oldretry.annotation;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 重试注解实现(新)
 *
 * @author ly
 * @date 2021/7/29
 */
@Log4j2
@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(com.utils.oldretry.annotation.Retry)")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();

        Retry r = method.getAnnotation(Retry.class);
        //读取注解传的值
        int maxCount = r.maxCount();
        int sleepTime  = r.sleepTime();
        for (int i = 0; i <= maxCount; i++) {
            try {
                log.info("方法"+ms.getName()+"第" + (i + 1) + "次执行");
                joinPoint.proceed();
            } catch (Exception e) {
                if (i == maxCount) {
                    log.error("方法"+ms.getName()+"超过最大重试次数，仍然报错:" + e);
                    break;
                }
                log.info("等待" + sleepTime + "毫秒");
                Thread.sleep(sleepTime);
                continue;
            }
            break;
        }
    }
}
