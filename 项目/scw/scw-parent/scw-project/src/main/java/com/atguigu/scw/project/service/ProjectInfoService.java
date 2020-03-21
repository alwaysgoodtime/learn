package com.atguigu.scw.project.service;

import com.atguigu.scw.project.bean.*;

import java.util.List;


public interface ProjectInfoService {

    List<TType> getProjectTypes();

    List<TTag> getAllProjectTags();

    TProject getProjectInfo(Integer projectId);

    List<TReturn> getProjectReturns(Integer projectId);

    List<TProject> getAllProjects();

    List<TProjectImages> getProjectImages(Integer id);

    TReturn getProjectReturnById(Integer retId);

}
