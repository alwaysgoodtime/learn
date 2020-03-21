package com.atguigu.scw.project.service;

import com.atguigu.scw.enums.ProjectStatusEnume;
import com.atguigu.scw.project.vo.req.ProjectRedisStorageVo;

/**
 * @author goodtime
 * @create 2020-02-25 4:02 下午
 */
public interface ProjectCreateService {
    void saveProjectInfo(ProjectStatusEnume auth, ProjectRedisStorageVo project);
}
