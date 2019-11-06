package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ProjecetServiceImp implements ProjecetService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjectList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        return projects;
    }

    @Override
    public Project getProjectDetail(Integer cid) {
        Project project = projectMapper.selectByPrimaryKey(cid);
        return project;
    }

    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        ProjectExample projectExample = new ProjectExample();
        ProjectExample.Criteria criteria = projectExample.createCriteria();
        criteria.andPidIn(list);
        int i = projectMapper.deleteByExample(projectExample);
        return i==ids.length;
    }

    @Override
    public Project getCustomerDetail(Integer pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        return project;
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public List<Project> projecetJsonList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        return projects;
    }

    @Override
    public List<Project> projecetWithAnalyseJsonList() {
        return projectMapper.projecetWithAnalyseJsonList();
    }
}
