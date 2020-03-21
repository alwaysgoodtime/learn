package com.atguigu.scw.webui.vo.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * 这是调用远程服务器服务的端口，需要导的包，远程服务器登录后，返回的对象就是UserRespVo
 * @author goodtime
 * @create 2020-02-26 10:23 下午
 */
@Data
//所有的VO和BEAN，如果需要在网络中传输，一定要实现序列化接口，前端调后端，否则会报错
public class UserRespVo implements Serializable {

    private String accessToken;

    //id和密码不返回
    //private Integer id;

    private String loginacct;//就是手机号

    //private String userpswd;

    private String username;

    private String email;

    private String authstatus = "0";
    //设置用户实名认证，默认值为0，1为审核中，2为认证成功

    private String usertype;

    private String realname;

    private String cardnum;

    private String accttype;
}

