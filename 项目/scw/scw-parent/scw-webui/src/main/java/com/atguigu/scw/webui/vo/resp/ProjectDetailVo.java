package com.atguigu.scw.webui.vo.resp;

import com.atguigu.scw.webui.bean.TReturn;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**页面详情Vo，封装返回项目详情页面的数据，注意：要返回有用的数据，没用的数据返回，徒占带宽
 * @author goodtime
 * @create 2020-02-27 1:19 下午
 */
@Data
public class ProjectDetailVo implements Serializable {

    private Integer memberid;// 会员id

//	//第二步：收集项目基本信息以及发起人信息（暂时不做）
//	private List<Integer> typeids; // 项目的分类id
//	private List<Integer> tagids; // 项目的标签id
//
    private String name;// 项目名称
    private String remark;// 项目简介
    private Long money;// 筹资金额
    private Integer day;// 筹资天数

    private String headerImage;// 项目头部图片

    //查询所有项目时直接用getDetailsImage了，所以要直接new一个，防止空指针异常
    private List<String> detailsImage = new ArrayList<String>();// 项目详情图片
    // 发起人信息：自我介绍，详细自我介绍，联系电话，客服电话

    //第三步：收集回报信息
    private List<TReturn> returns;// 项目回报

    private Integer id;

    private String status;

    private String deploydate;

    //设置一些初始的默认值
    private Long supportmoney;

    private Integer supporter;

    private Integer completion;

    private Integer follower = 100;

}
