package com.controller;

import com.thread.MyThread;
import com.thread.MyThreadRetry;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("service/newTest")
public class NewTestController {
    MyThread t1=new MyThread("t1",0,4);
    MyThread t2=new MyThread("t2",0,3);
 MyThreadRetry rt1=new MyThreadRetry("rt1",6,2000);
 MyThreadRetry rt2=new MyThreadRetry("rt2",4,1000);


    @GetMapping("/count")
    public void threadsCount() {
        t1.start();
       t2.start();
    }

    @GetMapping("/retry")
    public void threadsRetry() {
        rt1.start();
        rt2.start();
    }


}
