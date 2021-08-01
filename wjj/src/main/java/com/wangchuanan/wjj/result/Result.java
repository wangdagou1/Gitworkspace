package com.wangchuanan.wjj.result;

/**
 * 统一的后端返回实体类
 */
public class Result {

    //响应码
    private int code;
    //错误信息
    private String msg;
    //返回数据
    private  Object data;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code,String msg) {
        this.msg = msg;
    }

    public Result(int code,String message,Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

