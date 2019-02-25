package com.burning8393.template.model.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * UserQuery class
 *
 * @author : Pangxw
 * @date : 2019/2/25 16:07
 * @description :
 */
@Data
public class UserQuery {
    private Long id;

    @NotBlank(message = "code不能为空")
    private String code;

    @NotBlank(message = "name不能为空")
    private String name;
}
