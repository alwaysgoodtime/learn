package com.atguigu.scw.enums;

/**
 * 后台审核状态
 */
public enum AdminAuthStatusEnume {
    UN_AUTH((byte) 0, "未审核"),
    AUTHING((byte) 1, "审核中"),
    AUTHED((byte) 2, "审核通过"),
    AUTHFAIL((byte) 3, "审核不通过");

    private byte code;
    private String status;

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private AdminAuthStatusEnume(byte code, String status) {
        this.code = code;
        this.status = status;
    }


}
