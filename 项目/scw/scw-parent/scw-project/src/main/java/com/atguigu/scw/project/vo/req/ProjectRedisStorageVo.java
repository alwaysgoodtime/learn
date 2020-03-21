package com.atguigu.scw.project.vo.req;

import com.atguigu.scw.project.bean.TReturn;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 大VO，封装整个VO，不断与Redis交互，项目创建四步走完后，写到数据库中，项目创建完成
 *
 * @author goodtime
 * @create 2020-02-25 2:19 下午
 */
@Data
@ToString
//虽然私有属性不能被继承，但是get和set被继承，就能进行数据封装了
public class ProjectRedisStorageVo extends BaseVo {


    //项目的临时token(阅读并同意协议时，后台会分配一个项目token值，用来绑定当前项目信息)
    private String projectToken;//项目临时token，标志其为哪个项目，但不能给id，因为项目还没存到数据库里

    private Integer memberid;//会员id

    private List<Integer> typeids; //项目的分类id,可以多个
    private List<Integer> tagids; //项目的标签id

    private String name;//项目名称
    private String remark;//项目简介
    private Long money;//筹资金额
    private Integer day;//筹资天数

    //这里存的就是图片的url
    private String headerImage;//项目头部图片
    private List<String> detailsImage;//项目详情图片

    private List<TReturn> projectReturns;//项目回报

    private Integer follower = 100;

    //发起人信息：自我介绍，详细自我介绍，联系电话，客服电话

    //确认信息（易付宝和法人信息省略）
}
