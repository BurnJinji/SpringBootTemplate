package com.burning8393.template.biz.service.impl;

import com.burning8393.template.biz.entity.User;
import com.burning8393.template.biz.mapper.auto.UserMapper;
import com.burning8393.template.biz.service.UserService;
import com.burning8393.template.model.query.UserQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * UserServiceImpl class
 *
 * @author : Pangxw
 * @date : 2019/2/23 11:53
 * @description :
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    @Cacheable(value = "burning", key = "#id")
    public User fetchOne(long id) {
        log.info("-------------------数据库操作");
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<User> fetchPageUsers(int current, int size) {
        PageHelper.startPage(current, size);
        List<User> users = userMapper.selectByExample(null);
        return new PageInfo<>(users);
    }

    @Override
    @CacheEvict(value = "burning", key = "#query.id")
    public int update(UserQuery query) {
        if (Objects.isNull(query.getId())) return -1;
        User user = new User();
        user.setId(query.getId());
        user.setCode(query.getCode());
        user.setName(query.getName());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int insert(UserQuery query) {
        User user = new User();
        user.setCode(query.getCode());
        user.setName(query.getName());
        return userMapper.insertSelective(user);
    }
}
