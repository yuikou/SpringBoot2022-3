package com.atguigu.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//(proxyBeanMethods = true)  -> 保證依賴的組件始終是單實例
@Configuration(proxyBeanMethods = true)
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet() {

        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet, "/my");
    }

    @Bean
    public FilterRegistrationBean myfilter() {

        Myfilter myfilter = new Myfilter();
//        return new FilterRegistrationBean(myfilter, myServlet());

        FilterRegistrationBean fb = new FilterRegistrationBean(myfilter);
        fb.setUrlPatterns(Arrays.asList("/my", "/css/*"));

        return fb;
    }

    @Bean
    public ServletListenerRegistrationBean myListener() {
        MyServletContextListener myServletContextListener = new MyServletContextListener();
        return new ServletListenerRegistrationBean(myServletContextListener);
    }
}
