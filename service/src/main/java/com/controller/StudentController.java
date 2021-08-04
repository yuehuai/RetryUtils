package com.controller;

import com.aopdemo.LiuTest;
import com.service.StudentService;
import com.utils.count.CountExceptionUtil;
import com.utils.count.annotation.CountException;
import com.utils.oldretry.annotation.Retry;
import com.utils.retry.RetryUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller
 *
 * @author ly
 * @date 2021/7/28
 */
@Log4j2
@RestController
@RequestMapping("service/student")
public class StudentController {
    @Value("${count}")
    int count;
    @Value("${retry.maxCount}")
    int maxCount;
    @Value("${retry.sleepTime}")
    int sleepTime;

    @Autowired
    private StudentService studentService;


    @ApiOperation("自定义注解测试")
    @LiuTest
    @GetMapping("test")
    public void test() {
        System.out.println("111");
    }

    @ApiOperation("自定义重试注解测试")
    @GetMapping("test1")
//    @RetryProcess(value = 5, sleep = 1000)
    @CountException(maxCount = 5, methodName = "testRetry")
    public void testRetry() {
        System.out.println("方法执行!");
        Integer i = null;
        System.out.println(i + 1);
        System.out.println(2 / 0);


    }

    @ApiOperation("重试工具类测试")
    @GetMapping("test2")
    public void test2() {
        try {
            log.info("当前最大次数："+maxCount+"当前休眠时间："+sleepTime);
            // 最大重试次数,延迟重试时间
             RetryUtil.retry(maxCount, sleepTime,
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

    @ApiOperation("累计次数报错测试(含异常的方法)")
    @GetMapping("test3")
//    @CountException
    public void testCount() {
//        try {
//         studentService.retryTest();
//        } catch (Exception e) {
//            //最大报错累计数，方法名,异常
//            CountExceptionUtil.countException(4, "retryTest", e);
//        }

        studentService.retryTest();

    }


    @ApiOperation("累计次数报错测试(不含异常的方法)")
    @GetMapping("test4")
    public void testCountNoneE() {
        try {
            studentService.countTest2();
        } catch (Exception e) {
            //最大报错累计数，方法名,异常
            CountExceptionUtil.countException(4, "retryTest", e);
        }


    }

    @ApiOperation("测试新的重试注解")
    @GetMapping("testNewRetry")
    @Retry(maxCount = 4, sleepTime = 2000)
    public void testRetryAnnotation() {
        log.info("方法执行");
        System.out.println(2 / 0);
    }
}