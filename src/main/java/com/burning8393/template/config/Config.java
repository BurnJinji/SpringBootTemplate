package com.burning8393.template.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Pang Xiaowei
 * @title: Config
 * @projectName template
 * @description: config
 * @date 2019-02-2023:20
 */

@Component
@ConfigurationProperties(prefix = "config")
@Data
public class Config {

    String code;

    String name;

    List<String> hobby;
}
