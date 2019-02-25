package com.burning8393.template.biz.service.impl;

import com.burning8393.template.biz.service.UnitTestService;
import org.springframework.stereotype.Service;

/**
 * UnitTestServiceImpl class
 *
 * @author : Pangxw
 * @date : 2019/2/25 16:29
 * @description :
 */
@Service
public class UnitTestServiceImpl implements UnitTestService {
    @Override
    public String process(String msg) {
        return msg;
    }
}
