package com.burning8393.template.biz.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * DemoControllerTest class
 *
 * @author : Pangxw
 * @date : 2019/2/25 17:32
 * @description :
 */
@RunWith(SpringRunner.class)
// SpringBootTest 是 springboot用于测试的注解，可指定启动类或测试环境等，这里直接默认
// 因为是mock测试，在实际开发过程中，可指定其测试启动时为随机端口，避免了不必要的端口冲突。
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//测试单一接口时 ，也可利用注解@WebMvcTest 进行单一测试
//@WebMvcTest(DemoController.class)
public class DemoControllerTest {
//    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void beforeSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testDemo() throws Exception {
        String msg = "this is a mock test";
        MvcResult result = this.mockMvc.perform(get("/mock").param("msg", msg))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        Assert.assertEquals(msg, result.getResponse().getContentAsString());
    }
}
