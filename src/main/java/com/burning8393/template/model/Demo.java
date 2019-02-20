package com.burning8393.template.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Pang Xiaowei
 * @title: Demo
 * @projectName template
 * @description: 样板
 * @date 2019-02-2022:57
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demo {
    private String code;

    private String name;
}
