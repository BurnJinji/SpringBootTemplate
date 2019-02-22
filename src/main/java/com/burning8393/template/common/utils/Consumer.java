package com.burning8393.template.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Pang Xiaowei
 * @title: Consumer
 * @projectName template
 * @description: TODO
 * @date 2019-02-2209:29
 */
@Component
@RabbitListener(queues = "burning")
@Slf4j
public class Consumer {
    public void process(String message) {
        log.info("接收的消息为： {}" , message);
    }
}
