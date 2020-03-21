package com.atguigu.scw.enums;

/**
 * 实名认证状态
 */
public enum AuthEnume {

    UN_AUTH((byte) 0, "未实名认证"),
    AUTHING((byte) 1, "实名认证中"),
    AUTHED((byte) 2, "实名认证成功");

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

    private AuthEnume(byte code, String status) {
        this.code = code;
        this.status = status;
    }


}
