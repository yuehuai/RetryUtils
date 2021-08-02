package com.aopdemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class aspectImpl {
    @Pointcut("@annotation(com.aopdemo.LiuTest)")
    private void cut() {
        System.out.println("3");
    }

    @Around("cut()")
    public void around(ProceedingJoinPoint joinPoint)throws Throwable{
        System.out.println("1");
        try{
            joinPoint.proceed();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("4");
    }
    @Before("cut()")
    private void before(){
        System.out.println("2");
    }

    @After("cut()")
    private void after(){
        System.out.println("5");
    }
}
