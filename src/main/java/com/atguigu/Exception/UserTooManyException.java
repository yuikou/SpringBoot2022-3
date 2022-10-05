package com.atguigu.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "使用者數量太多")
public class UserTooManyException extends RuntimeException{

    public UserTooManyException(){}

    public UserTooManyException(String msg){
        super(msg);
    }
}
