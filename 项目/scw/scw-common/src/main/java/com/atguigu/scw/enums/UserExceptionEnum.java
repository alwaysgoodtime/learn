package com.atguigu.scw.enums;

public enum UserExceptionEnum {

    LOGINACCT_EXIST(1, "登陆账号已经存在"),
    EMAIL_EXIST(2, "邮箱已经存在"),
    LOGINACCT_LOCKED(3, "账号已经被锁定"),
    USER_SAVE_ERROR(4, "账号保存失败"),
    USER_NOT_EXIST(5, "用户名不存在"),
    USER_PASSWORD_ERROR(6, "用户密码错误");

    private int code;
    private String msg;


    private UserExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
