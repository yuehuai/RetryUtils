package com.retry;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * 重试注解实现
 *
 * @author ly
 * @date 2021/7/28
 */
@Log4j2
@Aspect
@Component
public class RetryProcessAspect {
    private AtomicInteger atomicInteger = new AtomicInteger(0);


    @AfterThrowing(pointcut=("@annotation(com.retry.RetryProcess)"))
    public void tryAgain(JoinPoint point) {
        try {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
             RetryProcess retryProcess = methodSignature.getMethod().getAnnotation(RetryProcess.class);

            if (atomicInteger.intValue() < retryProcess.maxCount()) {
                int i = atomicInteger.incrementAndGet();
                Thread.sleep(retryProcess.sleepTime() * i);
                log.info("开始重试第" + i + "次");
                MethodInvocationProceedingJoinPoint methodPoint = ((MethodInvocationProceedingJoinPoint) point);
                methodPoint.proceed();
            }
        } catch (Throwable throwable) {
            tryAgain(point);
        }
    }
}

