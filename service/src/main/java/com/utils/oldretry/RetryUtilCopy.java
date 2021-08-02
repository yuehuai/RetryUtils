package com.utils.oldretry;

import lombok.extern.log4j.Log4j2;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * 报异常重试工具类
 *
 * @author ly
 * @date 2021/7/28
 */
@Log4j2
public class RetryUtilCopy {
    /**
     * 重试方法
     * @param maxCount 最大重试次数
     * @param sleepTime 每次重试间隔时间
     * @return
     */
    public static <T> T retry(int maxCount, int sleepTime, Supplier<T> supplier, BiFunction<T, Exception, Boolean> consumer)
            throws Exception {
        T result = null;
        Exception exception = null;
        //统计当前重试次数
        int count = 0;
        //是否需要接着重试
        boolean callMethod = true;
        while (callMethod && count <= maxCount) {
            try {
                int k = count + 1;
                log.info("方法第" + k + "次执行");
                result = supplier.get();
            } catch (Exception e) {
                if (count > maxCount) {
                    log.info(e.getMessage()+"超过最大重试次数，报错");
                    throw e;
                }
                exception = e;

                //判断当前是否存在异常，返回结果是否符合要求
                callMethod = consumer.apply(result, exception);
                //根据判断的结果来选择是否延时（若不用继续重试则不延时，直接跳出循环）
                if (callMethod) {
                    count++;
                    if (maxCount != 0 && count <= maxCount) {
                        Thread.sleep(sleepTime);
                        log.info("延迟" + sleepTime / 1000 + "秒");
                    }
                }
            }

        }
        return result;
    }
}
