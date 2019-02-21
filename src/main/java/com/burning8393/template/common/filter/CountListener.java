package com.burning8393.template.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * CountListener class
 *
 * @author : Pangxw
 * @date : 2019/2/21 13:38
 * @description : 统计监听器
 */
@Slf4j
public class CountListener implements HttpSessionListener {
    private int count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        count++;
        se.getSession().getServletContext().setAttribute("count", count);
        log.info("新增在线人数， 当前在线人数：" + count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        count--;
        se.getSession().getServletContext().setAttribute("count", count);
        log.info("删减在线人数， 当前在线人数： " + count);
    }


}
