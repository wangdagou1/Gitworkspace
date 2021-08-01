package com.wangchuanan.wjj.util;

import com.wangchuanan.wjj.exceptions.ParamsException;

/**
 * 配合自定义异常ParamsException与全局异常处理GlobalExceptionHandlerResolver，抛出业务报错统一处理
 */
public class AssertUtil {
    public static void isTrue(Boolean flag,String msg){
        if(flag){
            throw new ParamsException(msg);
        }
    }
}
