package com.yd.taozi.Exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaotaozi on 2019/6/13.
 */
@ControllerAdvice
public class ExcepionController {
    @ExceptionHandler(value = UnauthorizedException.class)
    public String pao(){
        return "unauth";
    }
    @ExceptionHandler(value = ArithmeticException.class)
    public String paoo(){
        return "unauth";
    }
}
