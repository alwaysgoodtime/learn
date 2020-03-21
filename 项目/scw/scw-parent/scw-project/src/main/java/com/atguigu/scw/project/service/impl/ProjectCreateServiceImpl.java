package com.atguigu.scw.project.service.impl;

import com.aliyun.oss.common.utils.DateUtil;
import com.atguigu.scw.enums.ProjectStatusEnume;
import com.atguigu.scw.project.bean.*;
import com.atguigu.scw.project.constent.ProjectConstant;
import com.atguigu.scw.project.mapper.*;
import com.atguigu.scw.project.service.ProjectCreateService;
import com.atguigu.scw.project.vo.req.ProjectRedisStorageVo;
import com.atguigu.scw.utils.AppDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author goodtime
 * @create 2020-02-25 4:02 下午
 */
@Service
//这里用事物的默认配置即可
@Transactional
@Slf4j
public class ProjectCreateServiceImpl implements ProjectCreateService {


    @Autowired
    TProjectMapper tProjectMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    TProjectImagesMapper tProjectImagesMapper;


    @Autowired
    TReturnMapper tReturnMapper;

    @Autowired
    TProjectTypeMapper tProjectTypeMapper;

    @Autowired
    TProjectTagMapper tProjectTagMapper;

    @Override
    public void saveProjectInfo(ProjectStatusEnume auth, ProjectRedisStorageVo project) {

        //1.保存项目，直接对拷就行
        TProject tproject = new TProject();
        BeanUtils.copyProperties(project, tproject);
        tproject.setCreatedate(AppDateUtils.getFormatTime());
        tproject.setStatus(String.valueOf(auth.getCode()));

        //1.1拿到真实的项目id（原来一直存的是项目的临时token，存进数据库就有了项目id）
        //方便我们存后续表时有这个id（其他表的外键有些就是项目id）

        //主键回填的概念，这里可以设置，保存完之后让tproject的值更新，在xml文件中设置

        tProjectMapper.insertSelective(tproject);

        Integer id = tproject.getId();
        log.debug("项目id为{}", id);

        //2.保存图片

        String headerImage = project.getHeaderImage();
        List<String> detailsImages = project.getDetailsImage();

        //2.1存头图
        TProjectImages tProjectImages = new TProjectImages();
        tProjectImages.setProjectid(id);
        tProjectImages.setImgurl(headerImage);
        tProjectImages.setImgtype((byte) 0);
        tProjectImagesMapper.insertSelective(tProjectImages);

        //2.2存详情图

        for (String a : detailsImages
        ) {
            TProjectImages detailImage = new TProjectImages();
            tProjectImages.setProjectid(id);
            tProjectImages.setImgurl(a);
            tProjectImages.setImgtype((byte) 1);
            tProjectImagesMapper.insertSelective(tProjectImages);
        }


        //3.保存回报

        List<TReturn> returns = project.getProjectReturns();

        for (TReturn a : returns
        ) {
            TReturn tReturn = new TReturn();
            tReturn.setProjectid(id);
            BeanUtils.copyProperties(a, tReturn);
            tReturnMapper.insertSelective(tReturn);
        }

        //4.保存项目和分类关系

        List<Integer> typeids = project.getTypeids();

        for (Integer a : typeids
        ) {
            TProjectType tProjectType = new TProjectType();
            tProjectType.setProjectid(id);
            tProjectType.setTypeid(a);
            tProjectTypeMapper.insertSelective(tProjectType);
        }


        //5.保存项目和标签关系
        List<Integer> tagids = project.getTagids();
        for (Integer a : tagids
        ) {
            TProjectTag tProjectTag = new TProjectTag();
            tProjectTag.setProjectid(id);
            tProjectTag.setTagid(a);
            tProjectTagMapper.insertSelective(tProjectTag);
        }

        //6.保存发起人（省略）

        //7.保存法人（省略）

        //8.清理缓存
        stringRedisTemplate.delete(ProjectConstant.TEMP_PROJECT_PREFIX + project.getProjectToken());

        log.debug("项目保存成功");
    }
}
