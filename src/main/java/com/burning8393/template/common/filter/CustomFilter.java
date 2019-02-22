package com.burning8393.template.common.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * CustomFilter class
 *
 * @author : Pangxw
 * @date : 2019/2/21 11:58
 * @description : 自定义过滤器
 */
@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("customFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("CustomFilter doFilter" + req.getParameter("name"));
        filterChain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {
        log.info("customFilter destroy");
    }
}
