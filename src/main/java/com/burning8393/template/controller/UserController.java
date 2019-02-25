package com.burning8393.template.controller;

import com.burning8393.template.biz.entity.User;
import com.burning8393.template.biz.service.UserService;
import com.burning8393.template.common.utils.ResultHelper;
import com.burning8393.template.model.ResultWrapper;
import com.burning8393.template.model.query.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    UserService userService;

    @GetMapping("/page/{current}/{size}")
    public ResultWrapper fetchAllUsers(@PathVariable("current") int current, @PathVariable("size") int size) {
        return ResultHelper.success(userService.fetchPageUsers(current, size));
    }

    @GetMapping("/get/{id}")
    public ResultWrapper<User> fetchOne(@PathVariable("id") Long id) {
        return ResultHelper.success(userService.fetchOne(id));
    }

    @PostMapping("/one")
    public ResultWrapper<Integer> insert(UserQuery query) {
        return ResultHelper.success(userService.insert(query));
    }

    @PutMapping("/one")
    public ResultWrapper<Integer> update(UserQuery query) {
        return ResultHelper.success(userService.update(query));
    }
}
