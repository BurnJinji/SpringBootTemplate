package com.burning8393.template.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author Pang Xiaowei
 * @title: RabbitConfig
 * @projectName template
 * @description: TODO
 * @date 2019-02-2209:22
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue burningQueue() {
        return new Queue("burning");
    }
}
