package com.atguigu.scw.project.vo.req;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.scw.project.bean.TReturn;

import lombok.Data;
import lombok.ToString;

//项目首页展示的VO
@Data
@ToString
public class ProjectVo implements Serializable {

	private Integer id;//项目id
	private String name;// 项目名称
	private String remark;// 项目简介
    private String headerImage;// 项目头部图片

}
