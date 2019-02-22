package com.burning8393.template.controller;

import com.burning8393.template.biz.entity.User;
import com.burning8393.template.biz.mapper.auto.UserMapper;
import com.burning8393.template.common.utils.ResultHelper;
import com.burning8393.template.model.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Pang Xiaowei
 * @title: UserController
 * @projectName template
 * @description: TODO
 * @date 2019-02-2223:02
 */
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/all")
    public ResultWrapper fetchAllUsers() {
        List<User> users = userMapper.selectByExample(null);
        return ResultHelper.success(users);
    }
}
