package com.burning8393.template.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/demo")
    public String demo() {
        log.info("demo");
        return "hello SpringBoot";
    }
}
