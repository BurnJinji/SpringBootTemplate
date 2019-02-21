package com.burning8393.template.config.normal;

import com.burning8393.template.common.filter.CountListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ListenerConfig class
 *
 * @author : Pangxw
 * @date : 2019/2/21 13:43
 * @description :
 */
@Configuration
public class ListenerConfig {
    @Bean
    public ServletListenerRegistrationBean countListenerRegistrationBean() {
        return new ServletListenerRegistrationBean(new CountListener());
    }
}
