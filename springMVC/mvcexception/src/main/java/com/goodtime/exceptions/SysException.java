package com.goodtime.exceptions;

/**
 * 自定义异常类
 * @author goodtime
 * @create 2020-03-22 7:32 下午
 */
public class SysException extends Exception {

    //存储提示信息
    private  String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SysException(String message) {
        this.message = message;
    }
}
