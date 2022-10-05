package com.atguigu.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 處理整個web controller的異常
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class, NullPointerException.class})   //處理例外
    public String handleArithException(Exception e) {

        log.error("發生的例外為: {}", e);
//        例外處理完之後還是會返回一個modelAndView
        return "login"; //視圖名, 返回類型也可以是modelAndView
    }
}
