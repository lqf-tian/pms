package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;

import java.util.List;

public interface ProjecetService {
    void saveInfo(Project project);

    List<Project> getProjectList();

    Project getProjectDetail(Integer cid);

    boolean batchDelete(Integer[] ids);

    Project getCustomerDetail(Integer pid);

    void updateProject(Project project);

    List<Project> projecetJsonList();

    List<Project> projecetWithAnalyseJsonList();
}
