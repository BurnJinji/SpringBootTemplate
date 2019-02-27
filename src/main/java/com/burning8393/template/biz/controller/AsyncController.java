package com.burning8393.template.biz.controller;

import com.burning8393.template.biz.service.SyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.*;

/**
 * AsyncController class
 *
 * @author : Pangxw
 * @date : 2019/2/27 10:26
 * @description :
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    public static ExecutorService FIXED_THREAD_POOL = new ScheduledThreadPoolExecutor(5);

    @Autowired
    SyncService syncService;

    @GetMapping("/callable")
    public Callable<String> callable() {
        log.info("外部线程：" + Thread.currentThread().getName());
        return () -> {
            log.info("内部线程： " + Thread.currentThread().getName());
            return "callable";
        };
    }

    @GetMapping("/deferredResult")
    public DeferredResult<String> deferredResult() {
        log.info("外部线程： " + Thread.currentThread().getName());
        DeferredResult<String> result = new DeferredResult<>(60 * 1000L);
        result.onTimeout(() -> {
            log.error("DeferredResult超时了");
            result.setResult("超时了");
        });
        result.onCompletion(() -> {
            log.info("调用完成");
        });

        FIXED_THREAD_POOL.execute(() -> {
            log.info("内部线程： " + Thread.currentThread().getName());
            result.setResult("DeferredResult!!");
        });
        return result;
    }

    @GetMapping("/webAsyncTask")
    public WebAsyncTask<String> webAsyncTask() {
        log.info("外部线程： " + Thread.currentThread().getName());
        WebAsyncTask<String> result = new WebAsyncTask<>(60 * 1000L, () -> {
            log.info("外部线程： " + Thread.currentThread().getName());
            return "WebAsyncTask!!";
        });
        result.onTimeout(() -> "WebAsyncTask超时");
        result.onCompletion(() -> log.info("WebAsyncTask执行结束"));
        return result;
    }

    @GetMapping("/async")
    public String doAsync() throws InterruptedException {
        long start = System.currentTimeMillis();
        log.info("方法执行开始： {}", start);
        // 调用同步方法
        syncService.syncEvent();
        long syncTime = System.currentTimeMillis();
        log.info("同步方法用时: {}", syncTime - start);
        Future<String> doFuture = syncService.asyncEvent();
        while (true) {
            if (doFuture.isDone()) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
        long asyncTime = System.currentTimeMillis();
        log.info("异步方法用时： {}", asyncTime - syncTime);
        log.info("方法执行完成： {}", asyncTime);
        return "async!!";
    }
}
