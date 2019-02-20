package com.burning8393.template.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pang Xiaowei
 * @title: DemoController
 * @projectName template
 * @description: TODO
 * @date 2019-02-2022:41
 */
@RestController
public class DemoController {
    @GetMapping("/demo")
    public String demo() {
        return "hello SpringBoot";
    }
}
