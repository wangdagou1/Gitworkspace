package com.wangchuanan.wjj;

import com.wangchuanan.wjj.exceptions.ParamsException;
import com.wangchuanan.wjj.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandlerResolver {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e){

        Result result = new Result(500,"网络异常！请重试！");
        return result;

    }

    /**
     * 处理ParamsException
     * @param p
     * @return
     */
    @ExceptionHandler(value = ParamsException.class)
    @ResponseBody
    public Result paramarsExceptionHandler(ParamsException p){

        Result result = new Result(666,p.getMsg());
        return result;

    }

    /**
     * 处理BindException
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result bindExceptionHandler(MethodArgumentNotValidException exception){

        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        List<String> errors = new ArrayList<>();
        for (ObjectError allError : allErrors) {
            errors.add(allError.getDefaultMessage());
        }

        Result result = new Result(777,errors.toString());
        return result;

    }

}
