package com.burning8393.template.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ScheduledTask class
 *
 * @author : Pangxw
 * @date : 2019/2/27 14:52
 * @description :
 */
@Slf4j
@Component
public class ScheduledTask {
    /**
     * 自动扫描，启动时间点之后5秒执行一次
     */
    @Async("scheduledPoolTaskExecutor")
//    @Scheduled(fixedRate=5000)
    public void getCurrentDate() {
        log.info("Scheduled定时任务执行： " + new Date());
    }
}
