package com.utils.oldretry;

import lombok.extern.log4j.Log4j2;

/**
 * 报异常重试工具类
 *
 * @author ly
 * @date 2021/7/28
 */
@Log4j2
public class RetryUtil {
    /**
     * 重试方法
     *
     * @param maxCount  最大重试次数
     * @param sleepTime 每次重试间隔时间
     * @return
     */
    public static <T> void retry(int maxCount, int sleepTime, NewSupplier<T> supplier)
            throws Exception {

        for (int i = 0; i <= maxCount; i++) {
            try {
                log.info("方法第" + (i + 1) + "次执行");
                supplier.get();
            } catch (Exception e) {
                if (i == maxCount) {
                    log.error("方法超过最大重试次数，仍然报错:" + e);
                    throw e;
                }
                log.info("等待" + sleepTime + "毫秒");
                Thread.sleep(sleepTime);
                continue;
            }
            break;
        }

    }
}
