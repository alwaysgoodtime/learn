package com.atguigu.scw.user.vo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * vo：value object
 * 此为接受用户注册提交的值的封装类,值最好于bean实体类中的对象一样，方便对拷
 *
 * @author goodtime
 * @create 2020-02-24 7:46 下午
 */
//分别是lombok注解和swagger的注解
@ApiModel
@Data
public class UserRegistVo implements Serializable {
    @ApiModelProperty("手机号为账号")
    private String loginacct;
    @ApiModelProperty("密码")
    private String userpswd;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("0为个人，1为企业")
    private String usertype;

}
