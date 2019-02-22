package com.burning8393.template.controller;

import com.burning8393.template.config.Config;
import com.burning8393.template.model.DemoReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

}
