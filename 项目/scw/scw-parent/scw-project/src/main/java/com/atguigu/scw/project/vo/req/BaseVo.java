package com.atguigu.scw.project.vo.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 项目第一步的VO，也就是点击同意协议，封装的用户令牌，同时生成一个项目令牌
 *
 * @author goodtime
 * @create 2020-02-25 2:18 下午
 */
@Data
@ToString
public class BaseVo implements Serializable {
    private String accessToken;//用户令牌
}
