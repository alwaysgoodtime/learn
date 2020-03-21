package com.atguigu.scw.webui.vo.resp;

import com.atguigu.scw.webui.bean.TReturn;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class ProjectVo implements Serializable {


    private Integer id;//项目ID

	private String name;// 项目名称
	private String remark;// 项目简介
    private String headerImage;// 项目头部图片

}
