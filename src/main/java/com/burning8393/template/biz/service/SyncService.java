package com.burning8393.template.biz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * SyncService class
 *
 * @author : Pangxw
 * @date : 2019/2/27 11:48
 * @description :
 */
@Slf4j
@Component
public class SyncService {
    @Async("asyncPoolTaskExecutor")
    public Future<String> asyncEvent() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("异步方法内部线程名称： {}" , Thread.currentThread().getName());
        return new AsyncResult<>("异步方法返回值");
    }

    public void syncEvent() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
}
