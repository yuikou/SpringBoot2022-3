package com.atguigu.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登入檢查
 * 1. 配置好攔截器要攔截哪些請求
 * 2. 把這些配置放在容器中
 * 3.
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 目標方法執行之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登入檢查邏輯
        log.info("攔截的請求路徑是: {}", request.getRequestURI());

        //如loginUser不為null, 則放行
        if (!ObjectUtils.isEmpty(request.getSession().getAttribute("loginUser"))) return true;

        //攔截住為登入請求後要跳轉到登入葉面
        request.setAttribute("msg", "請先登入");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }

    /**
     * 目標方法執行完成之後
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 頁面渲染以後
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
