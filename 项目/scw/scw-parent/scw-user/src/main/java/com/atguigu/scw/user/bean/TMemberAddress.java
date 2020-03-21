package com.atguigu.scw.user.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class TMemberAddress implements Serializable {
    private Integer id;

    private Integer memberid;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}