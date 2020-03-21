package com.atguigu.scw.enums;

public enum AccttypeEnume {

    COMANY((byte) 0, "商业公司"),
    BUSINESS_MAN((byte) 1, "个体工商户"),
    PERSON((byte) 2, "个人经营"),
    GOVERNMENT((byte) 3, "政府非盈利组织");

    private byte code;
    private String type;

    private AccttypeEnume(byte code, String type) {
        this.code = code;
        this.type = type;
    }

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


}
