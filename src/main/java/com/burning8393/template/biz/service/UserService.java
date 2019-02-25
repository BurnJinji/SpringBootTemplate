package com.burning8393.template.biz.service;

import com.burning8393.template.biz.entity.User;
import com.burning8393.template.model.query.UserQuery;
import com.github.pagehelper.PageInfo;

/**
 * UserService class
 *
 * @author : Pangxw
 * @date : 2019/2/23 11:54
 * @description :
 */
public interface UserService {
    User fetchOne(long id);

    PageInfo<User> fetchPageUsers(int current, int size);

    int update(UserQuery user);

    int insert(UserQuery user);
}
