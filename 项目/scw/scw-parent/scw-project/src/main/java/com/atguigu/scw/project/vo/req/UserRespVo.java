package com.atguigu.scw.project.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author goodtime
 * @create 2020-02-25 12:23 上午
 */

@ApiModel
@Data
public class UserRespVo implements Serializable {

    @ApiModelProperty("访问令牌，请妥善保管，以后每次请求都要带上")
    //令牌 登录后会分配给当前用户一个临时令牌值，以后对系统的任何必须带，否则就拒绝访问;
    private String accessToken;

    //id和密码不返回
//    private Integer id;

    private String loginacct;//就是手机号

//  private String userpswd;

    private String username;

    private String email;

    private String authstatus = "0";
    //设置用户实名认证，默认值为0，1为审核中，2为认证成功

    private String usertype;

    private String realname;

    private String cardnum;

    private String accttype;
}
