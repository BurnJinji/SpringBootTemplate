package com.burning8393.template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * DemoReq class
 *
 * @author : Pangxw
 * @date : 2019/2/21 14:28
 * @description :
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoReq {
    @NotBlank(message= "code 不能为空")
    String code;

    @Length(max=10, message = "name长度不能超过10")
    String name;
}
