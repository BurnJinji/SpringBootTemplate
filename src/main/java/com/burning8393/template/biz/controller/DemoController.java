package com.burning8393.template.biz.controller;

import com.burning8393.template.config.Config;
import com.burning8393.template.model.query.DemoReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Pang Xiaowei
 * @title: DemoController
 * @projectName template
 * @description: demoController
 * @date 2019-02-2022:41
 */
@Slf4j
@RestController
public class DemoController {

    @Value("${blog.address}")
    String address;

    @Value("${blog.author}")
    String author;

    @Value("${blog.desc}")
    String desc;

    @Autowired
    AmqpTemplate rabbitmqTemplate;

    @Autowired
    Config config;

    @Autowired
    TaskScheduler taskScheduler;
    @GetMapping("/demo")
    public String demo() {
        log.info("demo");
        return "hello SpringBoot";
    }

    @GetMapping("/desc")
    public String desc() {
        return desc;
    }

    @GetMapping("/config")
    public Config config() {
        return config;
    }

    @GetMapping("/testListenerLogin")
    public String testListenerLogin(HttpServletRequest request) {
        log.info("当前在线人数： " + request.getSession().getId() + ": "
                + request.getSession().getServletContext().getAttribute("count"));
        return "Hello testListenerLogin";
    }

    @GetMapping("/testExceptionHandler")
    public String testExceptionHandler() {
        throw new RuntimeException("testExceptionHandler");
    }

    @GetMapping("/demo/valid")
    public String demoValid(@Valid DemoReq req) {
        return req.getCode() + ", " + req.getName();
    }

    @GetMapping("/send")
    public String send(String msg) {
        rabbitmqTemplate.convertAndSend("burning", msg);
        return "消息： " + msg + "，已发送";
    }

    @GetMapping("/mock")
    public String mock(String msg) {
        return msg;
    }

    @GetMapping("/timer")
    public String timer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("Timer定时任务启动： " + new Date());
            }
        }, 1000, 1000);
        return "timer";
    }

    @GetMapping("/executor")
    public String scheduledExecutorService() {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(5);
        service.scheduleAtFixedRate(() -> log.info("ScheduledExecutorService定时任务执行： " + new Date()),
                1, 1, TimeUnit.SECONDS);
        log.info("ScheduledExecutorService定时任务启动：" + new Date());
        return "ScheduledExecutorService";
    }

    @GetMapping("/poolTask")
    public String threadPoolTaskScheduler() {

        //每3秒执行一次
        taskScheduler.schedule(() -> log.info("ThreadPoolTaskScheduler定时任务：" + new Date()),
                new CronTrigger("0/3 * * * * ?"));
        return "ThreadPoolTaskScheduler!";
    }

}
