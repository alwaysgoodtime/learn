package com.atguigu.scw.enums;

public enum UserTypeEnume {

    USER_VIP0((byte) 0, "普通用户"),
    USER_VIP1((byte) 1, "铜牌用户"),
    USER_VIP2((byte) 2, "银牌用户"),
    USER_VIP3((byte) 3, "金牌用户"),
    USER_VIP4((byte) 4, "铂金用户"),
    USER_VIP5((byte) 5, "钻石用户"),
    COMPANY((byte) 6, "商业公司");

    private byte code;
    private String type;

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private UserTypeEnume(byte code, String type) {
        this.code = code;
        this.type = type;
    }


}
