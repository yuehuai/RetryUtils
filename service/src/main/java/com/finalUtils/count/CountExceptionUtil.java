package com.finalUtils.count;

import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 累计固定次数报错工具类
 *
 * @author ly
 * @date 2021/7/28
 */
@Log4j2
public class CountExceptionUtil {

    private static Map<String, AtomicInteger> exceptionCountMap = new ConcurrentHashMap<>();

    /**
     * 累计一定次数后报错
     * @param maxCount 最大累计次数
     * @param methodName 方法名
     * @param e 当前异常
     * @return
     */
    public static void countException(int maxCount, String methodName, Throwable e) {
        //拼接“方法名+(异常)”分类进行统计
        String key = methodName + '(' + e.getMessage() + ')';
        AtomicInteger atomicInteger  = exceptionCountMap.get(key);
        //Map中没有存储该异常时，初始化为0
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            exceptionCountMap.put(key,atomicInteger);
        }

        int i=atomicInteger.incrementAndGet();
        //超过设置的最大报错次数时报错
        if (i > maxCount) {
           log.info("出现"+key + "超过最大累计次数，报错");
            atomicInteger.set(0);
            log.info(exceptionCountMap);}
         else {
            log.info(key + "未超过最大次数，当前：" + i);
        }


    }


}

