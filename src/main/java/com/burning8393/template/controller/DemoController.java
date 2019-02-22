package com.burning8393.template.controller;

import com.burning8393.template.config.Config;
import com.burning8393.template.model.DemoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "Demo API")
@RestController
public class DemoController {

    @Value("${blog.address}")
    String address;

    @Value("${blog.author}")
    String author;

    @Value("${blog.desc}")
    String desc;

    @Autowired
    Config config;
    @GetMapping("/demo")
    public String demo() {
        log.info("demo");
        return "hello SpringBoot";
    }

    @ApiOperation("demo描述")
    @GetMapping("/desc")
    public String desc() {
        return desc;
    }

    @ApiOperation("demo 配置")
    @GetMapping("/config")
    public Config config() {
        return config;
    }

    @ApiOperation("demo 登陆")
    @GetMapping("/testListenerLogin")
    public String testListenerLogin(HttpServletRequest request) {
        log.info("当前在线人数： " + request.getSession().getId() + ": "
                + request.getSession().getServletContext().getAttribute("count"));
        return "Hello testListenerLogin";
    }

    @ApiOperation("demo 测试异常")
    @GetMapping("/testExceptionHandler")
    public String testExceptionHandler() {
        throw new RuntimeException("testExceptionHandler");
    }

    @ApiOperation("demo 测试验证效果")
    @GetMapping("/demo/valid")
    public String demoValid(@Valid DemoReq req) {
        return req.getCode() + ", " + req.getName();
    }
}
