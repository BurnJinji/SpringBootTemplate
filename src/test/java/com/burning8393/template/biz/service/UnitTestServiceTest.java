package com.burning8393.template.biz.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UnitTestServiceTest class
 *
 * @author : Pangxw
 * @date : 2019/2/25 16:31
 * @description :
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTestServiceTest {
    @Autowired
    UnitTestService testService;

    @Test
    public void test() {
        String msg = "this is a test";
        String result = testService.process(msg);
        Assert.assertEquals(msg, result);
    }
}
