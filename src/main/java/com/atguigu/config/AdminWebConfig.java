package com.atguigu.config;

import com.atguigu.interceptor.LoginInterceptor;
import com.atguigu.interceptor.RedisCountIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
1. 編寫一個攔截器, 實現HandlerInterceptor
2. 攔截器註冊到容器中(使用Config class, 實現WebMvcConfigurer的addInterceptor方法)
3. *** 指定攔截規則, [需注意靜態資源路路徑], 如果是攔截所有, 靜態資源也會被攔截
 */

/**
 * @EnableWebMvc: 全面接管
 *      靜態資源及視圖解析器, welcome page等等全部失效(可以自定義靜態資源行為)
 */
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    /**
     * filter, interceptor幾乎擁有相同的功能, 有啥區別?
     * 1. filter是servlet定義的原生組件, 脫離spring也能使用
     * 2. interceptor是spring定義的介面, 可以使用spring的自動裝配等功能
     */
    @Autowired
    RedisCountIntercepter redisCountIntercepter ;    //已經在容易裡面, 因此使用autowired注入即可

    /**
     * 定義靜態資源行為
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * 訪問 /aa/**的所有請求都去 classpath:/static/ 下面找到相對的路徑
         * http://localhost:8081/aa/css/bootstrap.min.css  需要加前置路徑"aa"才可進到頁面
         */
        registry.addResourceHandler("/aa/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")    //預設攔截所有請求, 包括靜態資源
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/js/**", "/images/**", "/aa/**");      //要放行的

        //此處注意不可new一個RedisCountIntercepter出來, 因為使用new的話, RedisCountIntercepter不被容器託管, 裡面注入的StringRedisTemplate將不會生效
        registry.addInterceptor(redisCountIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/js/**", "/images/**", "/aa/**");

    }
}
