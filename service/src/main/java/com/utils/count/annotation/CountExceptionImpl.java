package com.utils.count.annotation;

import com.utils.count.CountExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * CountException注解实现类
 *
 * @author ly
 * @date 2021/7/28
 */

@Log4j2
@Aspect
@Component
public class CountExceptionImpl {


    @Around("@annotation(com.utils.count.annotation.CountException)")
    public void around(ProceedingJoinPoint joinPoint)throws Throwable{
        int maxCount = 0;
        String methodName=null;
        try{
            MethodSignature ms = (MethodSignature) joinPoint.getSignature();
            Method method = ms.getMethod();
            CountException countException = method.getAnnotation(CountException.class);
            //读取注解传的值
            maxCount = countException.maxCount();
            methodName = countException.methodName();
            joinPoint.proceed();
        }catch (Exception e){
            CountExceptionUtil.countException(maxCount,methodName,e);
        }

    }




}
