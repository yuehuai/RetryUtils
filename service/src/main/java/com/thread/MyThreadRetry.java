package com.thread;

import com.constant.Constant;
import com.finalUtils.retry.RetryUtil;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@Log4j2
public class MyThreadRetry extends Thread {

    private Map<String, AtomicInteger> runCountMap=new HashMap<>();

    // 表示线程的名称
    private String name;
    private  int maxCount;
    private  int sleepTime;

    public MyThreadRetry(String name,int maxCount,int sleepTime) {
        // 通过构造方法配置name属性
        this.name = name;
        this.maxCount=maxCount;
        this.sleepTime=sleepTime;
    }

    @Override
    public void run() {  // 覆写run()方法，作为线程 的操作主体
        try {
            // 最大重试次数,延迟重试时间
            RetryUtil.retry(this.maxCount, this.sleepTime,
                    // 调用服务
                    () -> {
                        System.out.println(1/0);
                        System.out.println(1);
                    }
            );

        } catch (Exception e) {
            log.info("外层处理"+e.getMessage());
        }
    }


    public void ok() {
        AtomicInteger atomicInteger = Constant.RUN_COUNT_MAP.get("ok");
        if (atomicInteger == null) {
            System.out.println("为null.重置为0");
            atomicInteger = new AtomicInteger(0);
            Constant.RUN_COUNT_MAP.put("ok", atomicInteger);
        }
        int i = atomicInteger.incrementAndGet();
        System.out.println("线程"+this.name+"执行了第" + i + "次ok方法。");
    }

    public void error() {
        AtomicInteger atomicInteger = Constant.RUN_COUNT_MAP.get("error");
        if (atomicInteger == null) {

            atomicInteger = new AtomicInteger(0);
            Constant.RUN_COUNT_MAP.put("error", atomicInteger);
        }
        int i = atomicInteger.incrementAndGet();
        System.out.println(Constant.RUN_COUNT_MAP);
        System.out.println("线程"+this.name+"执行了第" + i + "次error方法。");
        System.out.println(2/0);
    }
}
