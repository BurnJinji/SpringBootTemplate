package com.burning8393.template.biz.service;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Rule;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitTestServiceTest {
    @Autowired
    UnitTestService testService;

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 100, threads = 10)
    public void test() {
        String msg = "this is a test";
        String result = testService.process(msg);
        Assert.assertEquals(msg, result);
    }
}
