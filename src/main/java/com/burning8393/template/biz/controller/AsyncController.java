package com.burning8393.template.biz.controller;

import lombok.extern.slf4j.Slf4j;
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
}
