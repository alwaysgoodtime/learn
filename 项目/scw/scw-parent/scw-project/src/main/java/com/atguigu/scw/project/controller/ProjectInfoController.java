package com.atguigu.scw.project.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import com.atguigu.scw.project.bean.*;
import com.atguigu.scw.project.component.OssTemplate;
import com.atguigu.scw.project.service.TMemberServiceFeign;
import com.atguigu.scw.project.vo.req.ProjectDetailVo;
import com.atguigu.scw.project.vo.req.ProjectVo;
import com.atguigu.scw.project.service.ProjectInfoService;
import com.atguigu.scw.project.vo.req.ReturnPayConfirmVo;
import com.atguigu.scw.project.vo.req.UserRespVo;
import com.atguigu.scw.vo.resp.AppResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "项目基本功能模块（文件上传、项目信息获取等）")
@Slf4j
@RequestMapping("/project")
@RestController
public class ProjectInfoController {

    @Autowired
    OssTemplate ossTemplate;

    @Autowired
    ProjectInfoService projectInfoService;

    @Autowired
    TMemberServiceFeign tMemberServiceFeign;



    @ApiOperation("获取项目信息详情（未查项目的tag和type）")
    @GetMapping("/details/info/{projectId}")
    public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId) {
        TProject p = projectInfoService.getProjectInfo(projectId);
        ProjectDetailVo projectDetailVo = new ProjectDetailVo();

        // 1、查出这个项目的所有图片
        List<TProjectImages> projectImages = projectInfoService.getProjectImages(p.getId());
        for (TProjectImages tProjectImages : projectImages) {
            if (tProjectImages.getImgtype() == 0) {
                projectDetailVo.setHeaderImage(tProjectImages.getImgurl());
            } else {
                List<String> detailsImage = projectDetailVo.getDetailsImage();
                detailsImage.add(tProjectImages.getImgurl());
            }
        }

        //2、项目的所有支持档位；
		List<TReturn> returns = projectInfoService.getProjectReturns(p.getId());
		projectDetailVo.setReturns(returns);

        BeanUtils.copyProperties(p, projectDetailVo);
        return AppResponse.ok(projectDetailVo);
    }

    @ApiOperation("获取项目某个回报档位的支付确认信息")
    @GetMapping("/details/returns/info/{projectId}/{returnId}")
    public AppResponse<ReturnPayConfirmVo> returnPayConfirmVo(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId) {

        try {
            TReturn tReturn = projectInfoService.getProjectReturnById(returnId);
            TProject projectInfo = projectInfoService.getProjectInfo(projectId);

            ReturnPayConfirmVo rPCV = new ReturnPayConfirmVo();

            rPCV.setProjectId(projectId);
            rPCV.setProjectName(projectInfo.getName());
            rPCV.setProjectRemark(projectInfo.getRemark());

            rPCV.setReturnId(tReturn.getId());
            rPCV.setReturnContent(tReturn.getContent());
            rPCV.setNum(1);
            rPCV.setPrice(tReturn.getSupportmoney());
            rPCV.setFreight(tReturn.getFreight());
            rPCV.setSignalpurchase(tReturn.getSignalpurchase());
            rPCV.setPurchase(tReturn.getPurchase());

            //封装最后的价格，现在是商品数量*商品单价+运费，以后计算复杂的话（折扣、代金券），要抽成一个微服务来做
            rPCV.setTotalPrice(new BigDecimal(rPCV.getNum()*rPCV.getPrice()+rPCV.getFreight()));

            //封装发起人信息和发起人名字，调用SCW-USER接口的服务

            AppResponse<UserRespVo> memberInfo = tMemberServiceFeign.getMemberInfo(projectInfo.getMemberid());

            UserRespVo data = memberInfo.getData();

            rPCV.setMemberId(projectInfo.getMemberid());//UserRespVo对象为了安全，并不会直接返回会员id，所以要到项目存的发起人那里取
            rPCV.setMemberName(data.getLoginacct());

            log.debug("封装的RPCV为{}",rPCV);

            return AppResponse.ok(rPCV);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("封装RPCV失败，服务器异常");
            AppResponse<ReturnPayConfirmVo> fail = AppResponse.fail(null);
            fail.setMsg("调用返回RPCV的服务失败，服务器异常");
            return fail;
        }
    }





    @ApiOperation("获取项目回报列表")
    @GetMapping("/details/returns/{projectId}")
    public AppResponse<List<TReturn>> detailsReturn(@PathVariable("projectId") Integer projectId) {

        List<TReturn> returns = projectInfoService.getProjectReturns(projectId);
        return AppResponse.ok(returns);
    }

    @ApiOperation("[+]获取项目某个回报档位信息")
    @GetMapping("/details/returns/info/{returnId}")
    public AppResponse<TReturn> returnInfo(@PathVariable("returnId") Integer returnId) {
        TReturn tReturn = projectInfoService.getProjectReturnById(returnId);
        return AppResponse.ok(tReturn);
    }

    @ApiOperation("[+]获取系统所有的项目分类")
    @GetMapping("/types")
    public AppResponse<List<TType>> types() {

        List<TType> types = projectInfoService.getProjectTypes();
        return AppResponse.ok(types);
    }

    @ApiOperation("[+]获取系统所有的项目标签")
    @GetMapping("/tags")
    public AppResponse<List<TTag>> tags() {
        List<TTag> tags = projectInfoService.getAllProjectTags();
        return AppResponse.ok(tags);
    }

    //大表禁止连接查询，可能会出现笛卡尔积的情况，需要我们一张表一张表的查
    //也就是我们一个一个查
    @ApiOperation("获取系统所有的项目")
    @GetMapping("/all")
    public AppResponse<List<ProjectVo>> all() {

        // 1、分步查询，先查出所有项目
        // 2、再查询这些项目图片
        List<ProjectVo> prosVo = new ArrayList<>();

        // 1、连接查询，所有的项目left join 图片表，查出所有的图片
        // left join：笛卡尔积 A*B 1000万*6 = 6000万
        // 大表禁止连接查询；
        List<TProject> pros = projectInfoService.getAllProjects();

        //遍历每个项目，获取项目所有图片，然后根据图片是详情图还是头图，将其和项目一道封装到ProjectVo中，最后返回ProjectVo的集合
        for (TProject tProject : pros) {
            Integer id = tProject.getId();
            List<TProjectImages> images = projectInfoService.getProjectImages(id);
            ProjectVo projectVo = new ProjectVo();
            BeanUtils.copyProperties(tProject, projectVo);

            for (TProjectImages tProjectImages : images) {
                if (tProjectImages.getImgtype() == 0) {
                    projectVo.setHeaderImage(tProjectImages.getImgurl());
                }
            }
            prosVo.add(projectVo);

        }

        return AppResponse.ok(prosVo);
    }

    /**
     * 上传图片功能
     * 三个要求：
     * 1.方法必须为post
     * 2.enctype="multipart/form-data"
     * 3.type="file"  表单必须是文件域
     * <p>
     * SpringMVC框架继承commmons-fileupload和commons-io组件，完成文件上传操作
     * SpringMVC提供文件上传解析器
     * Controller处理文件上传时，通过MultipartFile接受文件
     * <p>
     * /resources/bootstrap/xxxxx /static/css/xxx
     */
    @ApiOperation("文件上传功能")
    @PostMapping("/upload")

    public AppResponse<List> upload(@RequestParam("uploadfile") MultipartFile[] file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        List<String> list = null;
        try {
            list = new ArrayList<>();
            if (file != null && file.length > 0) {
                for (MultipartFile item : file) {
                    if (!item.isEmpty()) {
                        //因为上传文件的名字可能重复，可以加一个UUID生成的随机值，也可以加一个时间戳，不过时间戳在高并发下不知道撑不撑得住
                        String filename = UUID.randomUUID().toString().replaceAll("-", "") + "_" + item.getOriginalFilename();
                        String upload = ossTemplate.upload(filename, item.getInputStream());
                        list.add(upload);
                    }
                }
            }
            log.debug("ossTemplate信息：{},文件上传成功访问路径：{}", ossTemplate, list);
            return AppResponse.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            //服务内部错误
            log.error("上传文件{}失败", file);
            return AppResponse.fail(null);
        }
    }

}
