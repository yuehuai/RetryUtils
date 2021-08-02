package com.thread;

import com.constant.Constant;
import com.finalUtils.count.CountExceptionUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private  Map<String, AtomicInteger> runCountMap= new ConcurrentHashMap<>();

    // 表示线程的名称
    private String name;
    private  int okCount;
    private  int errorCount;

    public MyThread(String name,int okCount,int errorCount) {
        // 通过构造方法配置name属性
        this.name = name;
        this.okCount=okCount;
        this.errorCount=errorCount;
    }

    @Override
    public  void run() {  // 覆写run()方法，作为线程 的操作主体
        for (int i = 0; i <this.okCount; i++) {
            this.ok();
        }
        for (int i = 0; i <this.errorCount ; i++) {
           try{ this.error();}
           catch (Exception e){
               CountExceptionUtil.countException(5,"error",e);
           }
        }
    }


    public void ok() {
        AtomicInteger atomicInteger = Constant.RUN_COUNT_MAP.get("ok");
        if (atomicInteger == null) {
            System.out.println("为null.重置为0");
            atomicInteger = new AtomicInteger(0);
            Constant.RUN_COUNT_MAP.putIfAbsent("ok", atomicInteger);
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
